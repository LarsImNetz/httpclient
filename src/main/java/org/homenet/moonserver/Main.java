package org.homenet.moonserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Main {

	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	
	public Main() {
		LOGGER.trace("c'tor main()");
	}

	public static void main(String[] args) throws Exception {
		PostFile postFile = new PostFile();
		postFile.post();
	}

}
