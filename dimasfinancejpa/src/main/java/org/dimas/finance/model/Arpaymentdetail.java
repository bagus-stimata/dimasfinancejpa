package org.dimas.finance.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;


/**
 * The persistent class for the ARPAYMENTDETAIL database table.
 * 
 */
@Entity
@Table(name="ARPAYMENTDETAIL")
@NamedQueries({@NamedQuery(name="Arpaymentdetail.findAll", query="SELECT a FROM Arpaymentdetail a"),
	@NamedQuery(name="Arpaymentdetail.findAllByInvoiceAndDivRefresh", query="SELECT a From Arpaymentdetail a "
			+ " WHERE a.id.invoiceno LIKE :invoice AND a.id.division LIKE :division", 
			hints={ @QueryHint(name=QueryHints.REFRESH, value=HintValues.TRUE )})
})
public class Arpaymentdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArpaymentdetailPK id;

	@Column(name="CASHAMOUNTPAY")
	private double cashamountpay;

	@Column(name="GIROAMOUNTPAY")
	private double giroamountpay;

	@Column(name="INVOICERETUR")
	private String invoiceretur;

	@Column(name="INVOICETYPE")
	private String invoicetype;

	@Column(name="RETURAMOUNTPAY")
	private double returamountpay;

	@Column(name="TRANSFERAMOUNTPAY")
	private double transferamountpay;
	
	@Column(name="POTONGANAMOUNT")
	private double potonganamount;
	
	@Transient
	private double subtotalpay;

	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division")
	private Division divisionBean;
	

	//bi-directional many-to-one association to Area
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="bukugiroBean", insertable=true, updatable=true)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="divBukugiroBean", referencedColumnName="division"),
		@JoinColumn(name="refnoBukugiroBean", referencedColumnName="refno")})
	private Bukugiro bukugiroBean;
	
	//bi-directional many-to-one association to Area
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="bukutransferBean", insertable=true, updatable=true)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="divBukutransferBean", referencedColumnName="division"),
		@JoinColumn(name="refnoBukutransferBean", referencedColumnName="refno")})
	private Bukutransfer bukutransferBean;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="divInvoiceBean", referencedColumnName="division"),
		@JoinColumn(name="invoiceno", referencedColumnName="invoiceno"),
		@JoinColumn(name="tipeInvoiceBean", referencedColumnName="tipefaktur")})
	private Arinvoice invoiceBean;

	
	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="divReturBean", referencedColumnName="division"),
		@JoinColumn(name="returBean", referencedColumnName="invoiceno"),
		@JoinColumn(name="tipeReturBean", referencedColumnName="tipefaktur")})
	private Arinvoice returBean;
	
	
	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="division", referencedColumnName="division", insertable=false, updatable=false),
		@JoinColumn(name="refno", referencedColumnName="refno", insertable=false, updatable=false)})	
	private Arpaymentheader arpaymentheaderBean;
	
	public ArpaymentdetailPK getId() {
		return id;
	}


	public void setId(ArpaymentdetailPK id) {
		this.id = id;
	}


	public double getCashamountpay() {
		return cashamountpay;
	}


	public void setCashamountpay(double cashamountpay) {
		this.cashamountpay = cashamountpay;
	}


	public double getGiroamountpay() {
		return giroamountpay;
	}


	public void setGiroamountpay(double giroamountpay) {
		this.giroamountpay = giroamountpay;
	}


	public String getInvoiceretur() {
		return invoiceretur;
	}


	public void setInvoiceretur(String invoiceretur) {
		this.invoiceretur = invoiceretur;
	}


	public String getInvoicetype() {
		return invoicetype;
	}


	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}


	public double getReturamountpay() {
		return returamountpay;
	}


	public void setReturamountpay(double returamountpay) {
		this.returamountpay = returamountpay;
	}


	public double getTransferamountpay() {
		return transferamountpay;
	}


	public void setTransferamountpay(double transferamountpay) {
		this.transferamountpay = transferamountpay;
	}


	public double getPotonganamount() {
		return potonganamount;
	}


	public void setPotonganamount(double potonganamount) {
		this.potonganamount = potonganamount;
	}


	public Division getDivisionBean() {
		return divisionBean;
	}


	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}


	public Bukugiro getBukugiroBean() {
		return bukugiroBean;
	}


	public void setBukugiroBean(Bukugiro bukugiroBean) {
		this.bukugiroBean = bukugiroBean;
	}


	public Bukutransfer getBukutransferBean() {
		return bukutransferBean;
	}


	public void setBukutransferBean(Bukutransfer bukutransferBean) {
		this.bukutransferBean = bukutransferBean;
	}


	public Arpaymentheader getArpaymentheaderBean() {
		return arpaymentheaderBean;
	}


	public void setArpaymentheaderBean(Arpaymentheader arpaymentheaderBean) {
		this.arpaymentheaderBean = arpaymentheaderBean;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public double getSubtotalpay() {
		return subtotalpay;
	}


	public void setSubtotalpay(double subtotalpay) {
		this.subtotalpay = subtotalpay;
	}


	public Arinvoice getInvoiceBean() {
		return invoiceBean;
	}


	public void setInvoiceBean(Arinvoice invoiceBean) {
		this.invoiceBean = invoiceBean;
	}


	public Arinvoice getReturBean() {
		return returBean;
	}


	public void setReturBean(Arinvoice returBean) {
		this.returBean = returBean;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arpaymentdetail other = (Arpaymentdetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "" + id + "";
	}
	
	

}