package com.kalahgame.sixpits.controller;

import com.kalahgame.sixpits.Utility.impl.ResponseBuilderImpl;
import com.kalahgame.sixpits.Utility.impl.ValidationImpl;
import com.kalahgame.sixpits.controller.handler.AbstractionRestHandler;
import com.kalahgame.sixpits.exceptions.InvalidIdentifierException;
import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.enums.PitE;
import com.kalahgame.sixpits.service.impl.GameServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/games")
@Api(tags = {"games"})
public class GameController extends AbstractionRestHandler {

    @Autowired
    GameServiceImpl gameService;

    @RequestMapping(method = RequestMethod.POST , produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new game board")
    public String NewGame(final HttpServletRequest request){
        GameBoard gameBoard = gameService.AddGame();
        return new ResponseBuilderImpl().build(gameBoard.getId(),request);
    }

    @RequestMapping(method = RequestMethod.PUT , value = "/{gameId}/pits/{pitId}",produces="application/json")
    @ApiOperation(value = "Move and distribute stones of a pit", notes = "You have to provide a valid game board ID in the URL.Also a valid pit ID is needed too (range: 1-6)")
    public String Move(@ApiParam(value = "The ID of existing game board", required = true) @PathVariable @NotNull final String gameId, @ApiParam(value = "A pit number to distribute its stones (range: 1-6)", required = true) @PathVariable @NotNull final String pitId, final HttpServletRequest request) throws InvalidMovementException, InvalidIdentifierException {
        new ValidationImpl().validateIdentifier(pitId);
        GameBoard gameBoard = gameService.Move(gameId ,PitE.get(Integer.valueOf(pitId)));
        return new ResponseBuilderImpl().build(request,gameBoard);
    }
}
