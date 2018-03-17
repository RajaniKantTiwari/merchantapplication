package com.app.merchant.network.request.dashboard;

/**
 * Created by rajnikant on 17/03/18.
 */

public class UserSearchRequest {
    private final String search;

    public UserSearchRequest(String search) {
        this.search=search;
    }

    public String getSearch() {
        return search;
    }
}
