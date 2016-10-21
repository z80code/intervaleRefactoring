package com.intervale.models;

public enum Brand {

    VISA,
    MASTERCARD;

    public static Brand getBrandByCardNumber(String cardNumber) {

//        Первая цифра номера карты 4 = VISA
//        Первая цифра номера карты 5 = Mastercard

        switch (getNumberBrand(cardNumber)) {
            case 4:
                return Brand.VISA;
            case 5:
                return Brand.MASTERCARD;
        }

        return null;
    }

    private static int getNumberBrand(String cardNumber) {

        return Integer.parseInt(cardNumber.substring(0, 1));
    }

}
