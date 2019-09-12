package br.com.curso.jspavancado.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String FOLDER_RELATORIO = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static String SEPARATOR = File.separator;
	private final String caminhoArquivoRelatorio = null;
	private JRPdfExporter exporter = null;
	private String caminhoSubReport_dir = "";
	private File arquivoGerado = null;

	public String gerarRelatorio(List<?> litaDataBeanCollection, Map<String, Object> parametrosRealtorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws JRException {

		// Cria a lista de collectiondatasource de beans que carregam os dados para o
		// relatório
		JRBeanCollectionDataSource jSource = new JRBeanCollectionDataSource(litaDataBeanCollection);

		// Fornece o caminho fisico até a pasta que contém os relatórios .jasper
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIO);

		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIO).getPath();
			SEPARATOR = "";
		}

		// Caminho para imagens
		parametrosRealtorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

		// Caminho completo até o relatório compilado indicado
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		// Faz o carregamento dos relatórios
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);

		// Seta os parametros SUBREPORT_DIR com o caminho fisico para o subreport
		caminhoSubReport_dir = caminhoRelatorio + SEPARATOR;
		parametrosRealtorio.put(SUBREPORT_DIR, caminhoSubReport_dir);
		// Carrega o arquivo
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRealtorio, jSource);

		exporter = new JRPdfExporter();

		// Caminho para relatório exportado
		caminhoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";

		// Cria novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);

		// prepara a imprensão
		exporter.setExporterInput(new SimpleExporterInput(impressoraJasper));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
//		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(new SimplePdfExporterConfiguration());

		exporter.exportReport();

//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
//
//		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
//
//		exporter.exportReport();

		arquivoGerado.deleteOnExit();

		return caminhoArquivoRelatorio;
	}

}
