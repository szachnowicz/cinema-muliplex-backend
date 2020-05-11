package com.szachnowicz.cinema.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Getter
@Setter
public class ReservationResponse {

    private String confirmationUrl;
    private BigDecimal price;
    private List<String> seats;
    private Long movieHallId;
    private String movieName;

    ReservationResponse(String reservationUrl, BigDecimal price, List<String> seats, Long movieHallId, String movieName) {
        this.confirmationUrl = reservationUrl;
        this.price = price;
        this.seats = seats;
        this.movieHallId = movieHallId;
        this.movieName = movieName;
    }

    public static ReservationResponseBuilder builder() {
        return new ReservationResponseBuilder();
    }

    public static class ReservationResponseBuilder {
        private String reservationUrl;
        private BigDecimal price;
        private List<String> seats;
        private Long movieHallId;
        private String movieName;

        ReservationResponseBuilder() {
        }

        public ReservationResponseBuilder reservationUrl(String uuid, String port) {
            String ipAddress = "http://localhost:" + port;
            try {
                ipAddress = InetAddress.getLocalHost().getHostAddress() + ":" + port + "/";
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            reservationUrl = ipAddress + "cinema/api/confirmations/?code=" + uuid;
            return this;
        }

        public ReservationResponseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ReservationResponseBuilder seats(List<String> seats) {
            this.seats = seats;
            return this;
        }

        public ReservationResponseBuilder movieHallId(Long movieHallId) {
            this.movieHallId = movieHallId;
            return this;
        }

        public ReservationResponseBuilder movieName(String movieName) {
            this.movieName = movieName;
            return this;
        }

        public ReservationResponse build() {
            return new ReservationResponse(reservationUrl, price, seats, movieHallId, movieName);
        }

        public String toString() {
            return "ReservationResponse.ReservationResponseBuilder(confirmationUrl=" + this.reservationUrl + ", price=" + this.price + ", seats=" + this.seats + ", movieHallId=" + this.movieHallId + ", movieName=" + this.movieName + ")";
        }
    }
}



