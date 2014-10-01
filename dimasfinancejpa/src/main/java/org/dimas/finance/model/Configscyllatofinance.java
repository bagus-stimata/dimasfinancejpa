package org.dimas.finance.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CONFIGSCYLLATOFINANCE database table.
 * 
 */
@Entity
@Table(name="CONFIGSCYLLATOFINANCE")
@NamedQuery(name="Configscyllatofinance.findAll", query="SELECT c FROM Configscyllatofinance c")
public class Configscyllatofinance implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private long id;
    @Column(name = "NAMADIVISI")
    private String namaDivisi;
    @Column(name = "TANGGALTRANSAKSI")
    @Temporal(TemporalType.DATE)
    private Date tanggalTransaksi;
    @Column(name = "HOSTSCYLLA")
    private String hostScylla;
    @Column(name = "SSIDSCYLLA")
    private String ssidScylla;
    @Column(name = "DATABASESCYLLA")
    private String databaseScylla;
    @Column(name = "PORTSCYLLA")
    private String portScylla;
    @Column(name = "SCHEMASCYLLA")
    private String schemaScylla;
    @Column(name = "HOSTFINANCE")
    private String hostFinance;
    @Column(name = "KODESERVER")
    private String kodeServer;
    
    @Column(name = "SUMMARYTANGGALTRANSAKSI")
    @Temporal(TemporalType.DATE)
    private Date summaryTanggalTransaksi;
    @Column(name = "SUMMARYTIMETRANSFER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date summaryTimeTransfer;
    
    @Column(name = "MODETRANSFER")
    private String modeTransfer;
    @Column(name = "SUMMARYJUMLAHTRANSFER")
    private int summaryJumlahTransfer;
    @Column(name = "SUMMARYJUMLAHINVOICE")
    private int summaryJumlahInvoice;
    @Column(name = "SUMMARYNOMINALINVOICE")
    private double summaryNominalInvoice;
    @Column(name = "SUMMARYJUMLAHRETUR")
    private int summaryJumlahRetur;
    @Column(name = "SUMMARYNOMINALRETUR")
    private double summaryNominalRetur;
    
    @ManyToOne
    @JoinColumn(name = "division", referencedColumnName = "id")
    Division divisionBean;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaDivisi() {
        return namaDivisi;
    }

    public void setNamaDivisi(String namaDivisi) {
        this.namaDivisi = namaDivisi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getHostScylla() {
        return hostScylla;
    }

    public void setHostScylla(String hostScylla) {
        this.hostScylla = hostScylla;
    }

    public String getSsidScylla() {
        return ssidScylla;
    }

    public void setSsidScylla(String ssidScylla) {
        this.ssidScylla = ssidScylla;
    }

    public String getDatabaseScylla() {
        return databaseScylla;
    }

    public void setDatabaseScylla(String databaseScylla) {
        this.databaseScylla = databaseScylla;
    }

    public String getPortScylla() {
        return portScylla;
    }

    public void setPortScylla(String portScylla) {
        this.portScylla = portScylla;
    }

    public String getSchemaScylla() {
        return schemaScylla;
    }

    public void setSchemaScylla(String schemaScylla) {
        this.schemaScylla = schemaScylla;
    }

    public String getHostFinance() {
        return hostFinance;
    }

    public void setHostFinance(String hostFinance) {
        this.hostFinance = hostFinance;
    }

    public String getKodeServer() {
        return kodeServer;
    }

    public void setKodeServer(String kodeServer) {
        this.kodeServer = kodeServer;
    }

    public Date getSummaryTanggalTransaksi() {
        return summaryTanggalTransaksi;
    }

    public void setSummaryTanggalTransaksi(Date summaryTanggalTransaksi) {
        this.summaryTanggalTransaksi = summaryTanggalTransaksi;
    }

    public Date getSummaryTimeTransfer() {
        return summaryTimeTransfer;
    }

    public void setSummaryTimeTransfer(Date summaryTimeTransfer) {
        this.summaryTimeTransfer = summaryTimeTransfer;
    }

    public String getModeTransfer() {
        return modeTransfer;
    }

    public void setModeTransfer(String modeTransfer) {
        this.modeTransfer = modeTransfer;
    }

    public int getSummaryJumlahTransfer() {
        return summaryJumlahTransfer;
    }

    public void setSummaryJumlahTransfer(int summaryJumlahTransfer) {
        this.summaryJumlahTransfer = summaryJumlahTransfer;
    }

    public int getSummaryJumlahInvoice() {
        return summaryJumlahInvoice;
    }

    public void setSummaryJumlahInvoice(int summaryJumlahInvoice) {
        this.summaryJumlahInvoice = summaryJumlahInvoice;
    }

    public double getSummaryNominalInvoice() {
        return summaryNominalInvoice;
    }

    public void setSummaryNominalInvoice(double summaryNominalInvoice) {
        this.summaryNominalInvoice = summaryNominalInvoice;
    }

    public int getSummaryJumlahRetur() {
        return summaryJumlahRetur;
    }

    public void setSummaryJumlahRetur(int summaryJumlahRetur) {
        this.summaryJumlahRetur = summaryJumlahRetur;
    }

    public double getSummaryNominalRetur() {
        return summaryNominalRetur;
    }

    public void setSummaryNominalRetur(double summaryNominalRetur) {
        this.summaryNominalRetur = summaryNominalRetur;
    }

    public Division getDivisionBean() {
        return divisionBean;
    }

    public void setDivisionBean(Division divisionBean) {
        this.divisionBean = divisionBean;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Configscyllatofinance other = (Configscyllatofinance) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Configscyllatofinance{" + "id=" + id + '}';
    }


}
