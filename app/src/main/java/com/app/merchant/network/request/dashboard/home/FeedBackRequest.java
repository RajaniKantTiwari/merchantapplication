package com.app.merchant.network.request.dashboard.home;
/**
 * Created by rajnikant on 23/03/18.
 */

public class FeedBackRequest {
    private String order_id;
    private String feedback="";
    private String ratings;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
