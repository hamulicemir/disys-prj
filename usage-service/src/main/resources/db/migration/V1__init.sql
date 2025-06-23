CREATE TABLE usage_hourly (
                              hour TIMESTAMP PRIMARY KEY,
                              community_produced DECIMAL(10,3) NOT NULL DEFAULT 0,
                              community_used DECIMAL(10,3) NOT NULL DEFAULT 0,
                              grid_used DECIMAL(10,3) NOT NULL DEFAULT 0
);
CREATE TABLE current_percentage (
                                    hour TIMESTAMP PRIMARY KEY,
                                    community_depleted DECIMAL(5,2) NOT NULL,
                                    grid_portion DECIMAL(5,2) NOT NULL
);
