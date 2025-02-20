package config.pdfassistantlangchain4jastradb;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@SpringBootApplication
public class PdfAssistantLangchain4jAstradbApplication {

	private final EmbeddingStoreIngestor embeddingStoreIngestor;

	public PdfAssistantLangchain4jAstradbApplication(EmbeddingStoreIngestor embeddingStoreIngestor) {
		this.embeddingStoreIngestor = embeddingStoreIngestor;
	}

	@PostConstruct
	public void init() {
		Document document = loadDocument(toPath("RAG.pdf"), new ApachePdfBoxDocumentParser());
		embeddingStoreIngestor.ingest(document);
	}

	private static Path toPath(String fileName) {
		try {
			URL fileUrl =PdfAssistantLangchain4jAstradbApplication.class.getClassLoader().getResource(fileName);
			return Paths.get(fileUrl.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfAssistantLangchain4jAstradbApplication.class, args);
	}

}
