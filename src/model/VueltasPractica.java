package model;

import lombok.Data;

@Data
public class VueltasPractica {

    private String vuelta;
    private String timepo;
    private String errorPiloto;
    private String timepoNeto;
    private int AleD;
    private int AleT;
    private int Motor;
    private int frenos;
    private int caja;
    private int suspension;
    private String neumatico;
}
