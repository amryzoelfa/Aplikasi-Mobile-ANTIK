package com.example.antik.Model;

public class ModelRiwayat {
    String tanggal_periksa, id_poli, diagnosa, tindakan, resep_obat;

    public ModelRiwayat(){}

    public ModelRiwayat(String tanggal_periksa, String poli, String diagnosa, String tindakan, String obat) {
        this.tanggal_periksa = tanggal_periksa;
        this.id_poli = poli;
        this.diagnosa = diagnosa;
        this.tindakan = tindakan;
        this.resep_obat = obat;
    }

    public String getTanggal_periksa() {
        return tanggal_periksa;
    }

    public void setTanggal_periksa(String tanggal_periksa) {
        this.tanggal_periksa = tanggal_periksa;
    }

    public String getPoli(){
        return id_poli;
    }

    public void setPoli(String poli){
        this.id_poli = poli;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getObat() {
        return resep_obat;
    }

    public void setObat(String obat) {
        this.resep_obat = obat;
    }
}
