package com.example.exchange.service;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ExchangerHttpClientTest {

    @Autowired
    ExchangerHttpClient exchangerHttpClient;

    @Test
    public void testGetEnableCurrency() throws Exception {
        exchangerHttpClient = Mockito.spy(exchangerHttpClient);
        String spyUri = "https://v6.exchangerate-api.com/v6/2da8a6179931269acda3532clatest/USD";
        String spyResult = "{"
                + " \"result\":\"success\",\n"
                + " \"documentation\":\"https://www.exchangerate-api.com/docs\",\n"
                + " \"terms_of_use\":\"https://www.exchangerate-api.com/terms\",\n"
                + " \"time_last_update_unix\":1642809601,\n"
                + " \"time_last_update_utc\":\"Sat, 22 Jan 2022 00:00:01 +0000\",\n"
                + " \"time_next_update_unix\":1642896001,\n"
                + " \"time_next_update_utc\":\"Sun, 23 Jan 2022 00:00:01 +0000\",\n"
                + " \"base_code\":\"USD\",\n"
                + " \"conversion_rates\":{\n"
                + "  \"USD\":1,\n"
                + "  \"AED\":3.6725,\n"
                + "  \"AFN\":105.0118,\n"
                + "  \"ALL\":107.4339,\n"
                + "  \"AMD\":481.9761,\n"
                + "  \"ANG\":1.7900,\n"
                + "  \"AOA\":532.8167,\n"
                + "  \"ARS\":104.3144,\n"
                + "  \"AUD\":1.3880,\n"
                + "  \"AWG\":1.7900,\n"
                + "  \"AZN\":1.7002,\n"
                + "  \"BAM\":1.7248,\n"
                + "  \"BBD\":2.0000,\n"
                + "  \"BDT\":85.9856,\n"
                + "  \"BGN\":1.7245,\n"
                + "  \"BHD\":0.3760,\n"
                + "  \"BIF\":1993.8788,\n"
                + "  \"BMD\":1.0000,\n"
                + "  \"BND\":1.3427,\n"
                + "  \"BOB\":6.8724,\n"
                + "  \"BRL\":5.4374,\n"
                + "  \"BSD\":1.0000,\n"
                + "  \"BTN\":74.4484,\n"
                + "  \"BWP\":11.5285,\n"
                + "  \"BYN\":2.5736,\n"
                + "  \"BZD\":2.0000,\n"
                + "  \"CAD\":1.2550,\n"
                + "  \"CDF\":2002.2510,\n"
                + "  \"CHF\":0.9123,\n"
                + "  \"CLP\":804.9111,\n"
                + "  \"CNY\":6.3455,\n"
                + "  \"COP\":4020.7052,\n"
                + "  \"CRC\":634.1722,\n"
                + "  \"CUP\":24.0000,\n"
                + "  \"CVE\":97.2410,\n"
                + "  \"CZK\":21.4613,\n"
                + "  \"DJF\":177.7210,\n"
                + "  \"DKK\":6.5792,\n"
                + "  \"DOP\":57.6254,\n"
                + "  \"DZD\":140.0098,\n"
                + "  \"EGP\":15.7060,\n"
                + "  \"ERN\":15.0000,\n"
                + "  \"ETB\":49.9157,\n"
                + "  \"EUR\":0.8819,\n"
                + "  \"FJD\":2.1226,\n"
                + "  \"FKP\":0.7377,\n"
                + "  \"FOK\":6.5792,\n"
                + "  \"GBP\":0.7377,\n"
                + "  \"GEL\":3.0767,\n"
                + "  \"GGP\":0.7377,\n"
                + "  \"GHS\":6.5230,\n"
                + "  \"GIP\":0.7377,\n"
                + "  \"GMD\":53.3482,\n"
                + "  \"GNF\":9060.7208,\n"
                + "  \"GTQ\":7.6934,\n"
                + "  \"GYD\":209.3126,\n"
                + "  \"HKD\":7.7868,\n"
                + "  \"HNL\":24.5725,\n"
                + "  \"HRK\":6.6446,\n"
                + "  \"HTG\":101.3316,\n"
                + "  \"HUF\":315.4535,\n"
                + "  \"IDR\":14255.3337,\n"
                + "  \"ILS\":3.1482,\n"
                + "  \"IMP\":0.7377,\n"
                + "  \"INR\":74.4486,\n"
                + "  \"IQD\":1459.9115,\n"
                + "  \"IRR\":41938.2510,\n"
                + "  \"ISK\":128.4031,\n"
                + "  \"JEP\":0.7377,\n"
                + "  \"JMD\":155.4265,\n"
                + "  \"JOD\":0.7090,\n"
                + "  \"JPY\":113.8335,\n"
                + "  \"KES\":113.4872,\n"
                + "  \"KGS\":84.7424,\n"
                + "  \"KHR\":4073.1681,\n"
                + "  \"KID\":1.3880,\n"
                + "  \"KMF\":433.8588,\n"
                + "  \"KRW\":1191.6650,\n"
                + "  \"KWD\":0.2996,\n"
                + "  \"KYD\":0.8333,\n"
                + "  \"KZT\":435.8108,\n"
                + "  \"LAK\":11286.0617,\n"
                + "  \"LBP\":1507.5000,\n"
                + "  \"LKR\":202.8267,\n"
                + "  \"LRD\":150.2954,\n"
                + "  \"LSL\":15.0954,\n"
                + "  \"LYD\":4.5797,\n"
                + "  \"MAD\":9.1903,\n"
                + "  \"MDL\":18.0689,\n"
                + "  \"MGA\":3120.7644,\n"
                + "  \"MKD\":54.4065,\n"
                + "  \"MMK\":1777.9317,\n"
                + "  \"MNT\":2864.3569,\n"
                + "  \"MOP\":8.0204,\n"
                + "  \"MRU\":36.3253,\n"
                + "  \"MUR\":43.7962,\n"
                + "  \"MVR\":15.3914,\n"
                + "  \"MWK\":820.0730,\n"
                + "  \"MXN\":20.4842,\n"
                + "  \"MYR\":4.1873,\n"
                + "  \"MZN\":63.8228,\n"
                + "  \"NAD\":15.0954,\n"
                + "  \"NGN\":414.9456,\n"
                + "  \"NIO\":35.4332,\n"
                + "  \"NOK\":8.8689,\n"
                + "  \"NPR\":119.1175,\n"
                + "  \"NZD\":1.4862,\n"
                + "  \"OMR\":0.3845,\n"
                + "  \"PAB\":1.0000,\n"
                + "  \"PEN\":3.8357,\n"
                + "  \"PGK\":3.5218,\n"
                + "  \"PHP\":51.3723,\n"
                + "  \"PKR\":176.4444,\n"
                + "  \"PLN\":3.9911,\n"
                + "  \"PYG\":6803.7796,\n"
                + "  \"QAR\":3.6400,\n"
                + "  \"RON\":4.3536,\n"
                + "  \"RSD\":103.6853,\n"
                + "  \"RUB\":76.7383,\n"
                + "  \"RWF\":1045.2715,\n"
                + "  \"SAR\":3.7500,\n"
                + "  \"SBD\":8.0619,\n"
                + "  \"SCR\":13.5587,\n"
                + "  \"SDG\":438.6817,\n"
                + "  \"SEK\":9.1803,\n"
                + "  \"SGD\":1.3428,\n"
                + "  \"SHP\":0.7377,\n"
                + "  \"SLL\":11383.9825,\n"
                + "  \"SOS\":578.5951,\n"
                + "  \"SRD\":21.2654,\n"
                + "  \"SSP\":435.0432,\n"
                + "  \"STN\":21.6062,\n"
                + "  \"SYP\":2529.6854,\n"
                + "  \"SZL\":15.0954,\n"
                + "  \"THB\":32.9725,\n"
                + "  \"TJS\":11.2750,\n"
                + "  \"TMT\":3.4999,\n"
                + "  \"TND\":2.7788,\n"
                + "  \"TOP\":2.2586,\n"
                + "  \"TRY\":13.4367,\n"
                + "  \"TTD\":6.7606,\n"
                + "  \"TVD\":1.3880,\n"
                + "  \"TWD\":27.6492,\n"
                + "  \"TZS\":2310.5922,\n"
                + "  \"UAH\":28.3095,\n"
                + "  \"UGX\":3519.2776,\n"
                + "  \"UYU\":44.5306,\n"
                + "  \"UZS\":10726.1354,\n"
                + "  \"VES\":4.6137,\n"
                + "  \"VND\":22724.9706,\n"
                + "  \"VUV\":112.8384,\n"
                + "  \"WST\":2.5705,\n"
                + "  \"XAF\":578.4783,\n"
                + "  \"XCD\":2.7000,\n"
                + "  \"XDR\":0.7130,\n"
                + "  \"XOF\":578.4783,\n"
                + "  \"XPF\":105.2370,\n"
                + "  \"YER\":250.4333,\n"
                + "  \"ZAR\":15.0955,\n"
                + "  \"ZMW\":17.4687,\n"
                + "  \"ZWL\":112.0755\n"
                + " }\n"
                + "}";
        Mockito.when(exchangerHttpClient.getData(spyUri)).thenReturn(spyResult);
        String expectedRaw = "USD, AED, AFN, ALL, AMD, ANG, AOA, ARS, AUD, AWG, AZN, BAM, BBD, BDT, BGN, BHD, BIF, BMD, BND, BOB, BRL, BSD, BTN, BWP, BYN, BZD, CAD, CDF, CHF, CLP, CNY, COP, CRC, CUP, CVE, CZK, DJF, DKK, DOP, DZD, EGP, ERN, ETB, EUR, FJD, FKP, FOK, GBP, GEL, GGP, GHS, GIP, GMD, GNF, GTQ, GYD, HKD, HNL, HRK, HTG, HUF, IDR, ILS, IMP, INR, IQD, IRR, ISK, JEP, JMD, JOD, JPY, KES, KGS, KHR, KID, KMF, KRW, KWD, KYD, KZT, LAK, LBP, LKR, LRD, LSL, LYD, MAD, MDL, MGA, MKD, MMK, MNT, MOP, MRU, MUR, MVR, MWK, MXN, MYR, MZN, NAD, NGN, NIO, NOK, NPR, NZD, OMR, PAB, PEN, PGK, PHP, PKR, PLN, PYG, QAR, RON, RSD, RUB, RWF, SAR, SBD, SCR, SDG, SEK, SGD, SHP, SLL, SOS, SRD, SSP, STN, SYP, SZL, THB, TJS, TMT, TND, TOP, TRY, TTD, TVD, TWD, TZS, UAH, UGX, UYU, UZS, VES, VND, VUV, WST, XAF, XCD, XDR, XOF, XPF, YER, ZAR, ZMW, ZWL";
        List<String> expected = List.of(expectedRaw.split(", "));
        List<String> actual = exchangerHttpClient.getEnableCurrency();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetRate() throws Exception {
        exchangerHttpClient = Mockito.spy(exchangerHttpClient);
        String spyUri = "https://v6.exchangerate-api.com/v6/2da8a6179931269acda3532c/pair/USD/UAH";
        String spyResult = "{\"result\":\"success\",\"documentation\":\"https://www.exchangerate-api.com/docs\",\"terms_of_use\":\"https://www.exchangerate-api.com/terms\",\"time_last_update_unix\":1642809601,\"time_last_update_utc\":\"Sat, 22 Jan 2022 00:00:01 +0000\",\"time_next_update_unix\":1642896001,\"time_next_update_utc\":\"Sun, 23 Jan 2022 00:00:01 +0000\",\"base_code\":\"USD\",\"target_code\":\"UAH\",\"conversion_rate\":28.3095}\n";
        Mockito.when(exchangerHttpClient.getData(spyUri)).thenReturn(spyResult);
        BigDecimal expected = BigDecimal.valueOf(28.309499740600586);
        BigDecimal actual = exchangerHttpClient.getRate("USD", "UAH");
        Assertions.assertEquals(expected, actual);
    }
}