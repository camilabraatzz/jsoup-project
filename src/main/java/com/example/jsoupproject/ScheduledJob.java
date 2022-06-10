package com.example.jsoupproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ScheduledJob {
	@Scheduled(fixedRate = 300000)
	@GetMapping
	public List<Servico> execute() throws InterruptedException, SQLException {
		System.out.println("Rodando " + LocalTime.now());
		try {
			Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();

			Connection conexao = FabricaConexao.getConexao();

			for (Element row : doc.select("table.tabelaListagemDados")) {

				final Elements colunaStatus = row.select("td:nth-of-type(6)");
				final Elements colunaEstados = row.select("td:nth-of-type(1)");

				String estado = "";
				String statusServico = "";

				DAO dao = new DAO();

				for (int index = 0; index < colunaStatus.size(); index++) {
					
					estado = colunaEstados.get(index).text();

					if (((String) colunaStatus.get(index).html()).contains("verde")) {
						statusServico = "Positivo";
					} else if (((String) colunaStatus.get(index).html()).contains("amarela")) {
						statusServico = "Negativo";
					} else if (((String) colunaStatus.get(index).html()).contains("vermelho")) {
						statusServico = "Negativo";
					}
					String sql = "INSERT INTO servicos (estado, status, data) VALUES (?, ?, ?)";

					System.out.println(dao.incluir(sql, estado, statusServico, LocalDate.now().toString()));
				}
				dao.close();
				
				String select = "SELECT * FROM servicos";

				Statement stmt = conexao.createStatement();

				ResultSet resultado = stmt.executeQuery(select);

				List<Servico> servicos = new ArrayList<>();

				while (resultado.next()) {
					int id = resultado.getInt("id");
					String estadoObj = resultado.getString("estado");
					String statusObj = resultado.getString("status");
					Date dataObj = resultado.getDate("data");

					servicos.add(new Servico(id, estadoObj, statusObj, dataObj));
				}
				return servicos;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
