package com.maveware.notariadigital.model;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by GOAM on 12/11/2016.
 */
public class PagosPorPlazo implements Parcelable{
    private int numeroDePago;
    private String totalNeto;
    private double montoCapital;
    private double pagoCapital;
    private double pagoInteres;
    private double abono;

    public PagosPorPlazo(){};

    public PagosPorPlazo(Parcel source) {
        this.montoCapital = source.readDouble();
        this.pagoCapital = source.readDouble();
        this.pagoInteres = source.readDouble();
        this.abono = source.readDouble();
        this.totalNeto = source.readString();
        this.numeroDePago = source.readInt();
    }

    public int describeContents() {
        return this.hashCode();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.montoCapital);
        dest.writeDouble(this.pagoCapital);
        dest.writeDouble(this.pagoInteres);
        dest.writeDouble(this.abono);
        dest.writeString(this.totalNeto);
        dest.writeInt(this.numeroDePago);
    }

    public static final Creator<PagosPorPlazo> CREATOR
            = new Creator<PagosPorPlazo>() {
        public PagosPorPlazo createFromParcel(Parcel in) {
            return new PagosPorPlazo(in);
        }

        public PagosPorPlazo[] newArray(int size) {
            return new PagosPorPlazo[size];
        }
    };

    public PagosPorPlazo(double montoCapital, double pagoCapital, double pagoInteres, double abono, String totalNeto, int numeroDePago){
        this.montoCapital = montoCapital;
        this.pagoCapital = pagoCapital;
        this.pagoInteres = pagoInteres;
        this.abono = abono;
        this.numeroDePago = numeroDePago;
    }

    public double getMontoCapital() {
        return montoCapital;
    }

    public void setMontoCapital(double montoCapital) {
        this.montoCapital = montoCapital;
    }

    public double getPagoCapital() {
        return pagoCapital;
    }

    public void setPagoCapital(double pagoCapital) {
        this.pagoCapital = pagoCapital;
    }

    public double getPagoInteres() {
        return pagoInteres;
    }

    public void setPagoInteres(double pagoInteres) {
        this.pagoInteres = pagoInteres;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(String totalNeto) {
        this.totalNeto = totalNeto;
    }

    public int getNumeroDePago() {
        return numeroDePago;
    }

    public void setNumeroDePago(int numeroDePago) {
        this.numeroDePago = numeroDePago;
    }
}
