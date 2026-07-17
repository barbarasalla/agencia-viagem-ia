package dev.ia;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.enterprise.inject.spi.CDI;

import java.util.function.Supplier;

public class RagRetrievalAugmentorSupplier implements Supplier<RetrievalAugmentor> {

    @Override
    public RetrievalAugmentor get() {

        EmbeddingStore<TextSegment> embeddingStore =
                CDI.current().select(EmbeddingStore.class).get();

        EmbeddingModel embeddingModel =
                CDI.current().select(EmbeddingModel.class).get();

        return DefaultRetrievalAugmentor.builder()
                .contentRetriever(
                        EmbeddingStoreContentRetriever.builder()
                                .embeddingStore(embeddingStore)
                                .embeddingModel(embeddingModel)
                                .maxResults(5)
                                .build()
                )
                .build();
    }
}