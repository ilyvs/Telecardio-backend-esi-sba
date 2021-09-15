package dz.esi.cardiodata.entity;


import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;


@Measurement(name = "ecg")
@Data
public class ECGMappedDB {

    @Column
    Double value;

    @Column(timestamp = true)
    Instant time;
}
