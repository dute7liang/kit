package test;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: zl
 * @Date: 2019-8-29 14:36
 */
@Slf4j
public class Test {

	public static void main(String[] args) {
		BigDecimal bigDecimal = BigDecimal.valueOf(123D);
		log.info(bigDecimal.toString());


	}

}
