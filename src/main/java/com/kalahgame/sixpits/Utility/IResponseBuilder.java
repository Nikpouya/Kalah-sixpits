package com.kalahgame.sixpits.Utility;

import com.kalahgame.sixpits.model.entities.GameBoard;
import javax.servlet.http.HttpServletRequest;

public interface IResponseBuilder {
    String build(String id, HttpServletRequest request);
    String build(HttpServletRequest request , GameBoard gameBoard);
}
