package model.filter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class highPriceFilter extends Filter {
    private Double minPrice;
    private Double maxPrice;
    private String buyerName;
    private LocalDateTime from;
    private LocalDateTime to;


    public highPriceFilter(Double minPrice, Double maxPrice, String buyerName, LocalDateTime from, LocalDateTime to) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.buyerName = buyerName;
        this.from = from;
        this.to = to;

    }

    @Override
    public String buildQuery() {
        StringBuilder query = new StringBuilder();
        if (minPrice != null) {
            query.append(" AND car_price >= ").append(minPrice);
        }
        if (maxPrice != null) {
            query.append(" AND car_price <= ").append(maxPrice);
        }
        if (from != null) {
            query.append(" AND purchase_date >= '").append(from.format(DateTimeFormatter.ISO_LOCAL_DATE)).append("'");
        }
        if (to != null) {
            query.append(" AND purchase_date <= '").append(to.format(DateTimeFormatter.ISO_LOCAL_DATE)).append("'");
        }

        return query.toString();
    }
}
