package com.example.jsoupproject;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class main {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx?AspxAutoDetectCookieSupport=1").get();
	}
}