package com.agoda.downloader;

import com.agoda.downloader.controller.DownloaderController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class DownloaderApplicationTests {

	@Autowired
	private DownloaderController downloaderController;

	private static final String URL_FTP = "ftp://ftp.gnu.org/gnu/bc/bc-1.07.1.tar.gz";
	private static final String URL_HTTP = "https://www.oracle.com/technetwork/java/newtojava/java8book-2172125.pdf";
//	private static final String URL_HTTP_HEAVY_FILE = "https://speed.hetzner.de/100MB.bin";
	private List<String> urls;

	@BeforeEach
	public void setUp() {
		urls = Arrays.asList(URL_FTP, URL_HTTP);
	}


	@Test
	@DisplayName("Exits controller")
	void contextLoads() {
		Assertions.assertNotNull(downloaderController);
	}

	@Test
	@DisplayName("Process controller to download files from uri's")
	void downloadFile() {

		CompletableFuture<Void> voidCompletableFuture = downloaderController.processFiles(urls);

		Void join = voidCompletableFuture.join();
	}

	@Test
	@DisplayName("Process controller to download files from Mocks")
	void downloadFileFromMocks() {


	}
}
