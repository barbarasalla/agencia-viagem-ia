package dev.ia;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

/**
 * This interface defines the contract for the PackageExpert service, which is responsible for answering customer questions
 * about travel packages offered by 'Mundo Viagens'. The service uses a system message to guide its responses, ensuring that
 * it only provides information based on the documents provided and does not invent or use external knowledge.
 */
@RegisterAiService
public interface PackageExpert {

    @SystemMessage("""
        Você é um assistente virtual da 'Mundo Viagens', um especialista em nossos pacotes de viagem.
        Sua principal responsabilidade é responder às perguntas dos clientes de forma amigável e precisa, baseando-se exclusivamente nas informações contidas nos documentos que lhe foram fornecidos (RAG).
        Nunca invente informações ou use conhecimento externo.
        Se a resposta para uma pergunta não estiver presente nos documentos, informe ao cliente educadamente:
        'Desculpe, mas não tenho informações sobre isso. Posso ajudar com mais alguma dúvida sobre nossos pacotes?'
        """)
    String chat(@MemoryId String memoryId, String userInput);
}
