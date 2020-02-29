package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BoardMembersResponse {

    @SerializedName("board_members")
    @Expose
    List<Members> boardMembersResponses = new ArrayList<>();

    public BoardMembersResponse(List<Members> boardMembersResponses) {
        this.boardMembersResponses = boardMembersResponses;
    }

    public List<Members> getBoardMembersResponses() {
        return boardMembersResponses;
    }

    public void setBoardMembersResponses(List<Members> boardMembersResponses) {
        this.boardMembersResponses = boardMembersResponses;
    }
}
