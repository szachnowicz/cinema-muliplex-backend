package com.szachnowicz.cinema.contoller;

import com.szachnowicz.cinema.dto.error.BusinessErrorCodes;
import com.szachnowicz.cinema.dto.error.BusinessException;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;


class DateUtilTest {

    @Test
    void parseTests() {
        LocalDateTime parse1 = DateUtil.parse("2020-04-30 12:21");


        assertEquals(parse1.getYear(), 2020);
        assertEquals(parse1.getMonth().getValue(), 4);
        assertEquals(parse1.getDayOfMonth(), 30);
        assertEquals(parse1.getHour(), 12);
        assertEquals(parse1.getMinute(), 21);
    }

    @Test
    void secondTest() {
        LocalDateTime parse1 = DateUtil.parse("2040-04-30 12:59");


        assertEquals(parse1.getYear(), 2040);
        assertEquals(parse1.getMonth().getValue(), 4);
        assertEquals(parse1.getDayOfMonth(), 30);
        assertEquals(parse1.getHour(), 12);
        assertEquals(parse1.getMinute(), 59);
    }


    @Test()
    void testOfWrongFormat() {
        BusinessException businessException = expectThrows(BusinessException.class, () -> DateUtil.parse("2020-04-30 12"));
        assertEquals(businessException.getErrorCode().name(), BusinessErrorCodes.WRONG_DATE_FORMAT.name());

        BusinessException businessException1 = expectThrows(BusinessException.class, () -> DateUtil.parse("12:21:11"));
        assertEquals(businessException1.getErrorCode().name(), BusinessErrorCodes.WRONG_DATE_FORMAT.name());
    }
}

