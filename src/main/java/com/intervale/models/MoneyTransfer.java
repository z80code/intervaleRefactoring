package com.intervale.models;

import java.math.BigDecimal;

public class MoneyTransfer {

    private int transferId;
    private Card clientCard;
    private Card subClientCard;
    private long dateTime;
    private Currency currency;
    private BigDecimal amount;
    private BigDecimal commission;

    public Boolean isError;
    public String status;
    public double commissionOfAmount;
    public double resultOperation;

    public MoneyTransfer() {
    }

    public MoneyTransfer(
            int transferId,
            Card clientCard,
            Card subClientCard,
            long dateTime,
            Currency currency,
            BigDecimal amount,
            BigDecimal commission) {
        this.transferId = transferId;
        this.clientCard = clientCard;
        this.subClientCard = subClientCard;
        this.dateTime = dateTime;
        this.currency = currency;
        this.amount = amount;
        this.commission = commission;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public void setClientCard(Card clientCard) {
        this.clientCard = clientCard;
    }

    public void setSubClientCard(Card subClientCard) {
        this.subClientCard = subClientCard;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public int getTransferId() {
        return transferId;
    }

    public Card getClientCard() {
        return clientCard;
    }

    public Card getSubClientCard() {
        return subClientCard;
    }

    public long getDateTime() {
        return dateTime;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyTransfer that = (MoneyTransfer) o;

        if (getDateTime() != that.getDateTime()) return false;
        if (!getClientCard().equals(that.getClientCard())) return false;
        if (!getSubClientCard().equals(that.getSubClientCard())) return false;
        if (getCurrency() != that.getCurrency()) return false;
        if (!getAmount().equals(that.getAmount())) return false;
        return getCommission().equals(that.getCommission());

    }

    @Override
    public int hashCode() {
        int result = getClientCard().hashCode();
        result = 31 * result + getSubClientCard().hashCode();
        result = 31 * result + (int) (getDateTime() ^ (getDateTime() >>> 32));
        result = 31 * result + getCurrency().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getCommission().hashCode();
        return result;
    }
}
