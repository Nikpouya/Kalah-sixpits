package com.kalahgame.sixpits.Utility.impl;

import com.kalahgame.sixpits.Utility.IResponseBuilder;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.PlaySection;
import net.minidev.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ResponseBuilderImpl implements IResponseBuilder {

    /**
     * @param id Id of created Kalah game board
     * @param request HttpServletRequest
     * @return Response body in String type
     */
    public final String build(String id, HttpServletRequest request)  {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("url",request.getRequestURL().append("/").append(id).toString());
            return jsonObject.toString();
    }

    /**
     * @param request HttpServletRequest
     * @param gameBoard Kalah game board
     * @return Response body in String type
     */
    public final String build(HttpServletRequest request , GameBoard gameBoard) {

            List<PlaySection> bothSections = gameBoard.getBothSections();
            ConcurrentSkipListMap<String ,String> sections = new ConcurrentSkipListMap<>();
            AtomicInteger counter = new AtomicInteger(1);
            for(PlaySection section: bothSections)
            {
                section.getPits().forEach(t-> sections.put(String.valueOf(counter.getAndIncrement()),String.valueOf(t.getStones())));
                sections.put(String.valueOf(counter.getAndIncrement()),String.valueOf(section.getKalah().getStones()));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", gameBoard.getId());
            jsonObject.put("url" , request.getScheme().concat("://").concat(request.getServerName()).concat(":").concat(String.valueOf(request.getServerPort())).concat("/").concat(gameBoard.getId()));
            jsonObject.put("status",sections);
            return jsonObject.toString();
    }
}
