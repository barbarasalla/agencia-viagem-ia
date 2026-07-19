package dev.ia;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Esta classe implementa um guardrail de saída que valida o tom das respostas geradas pela IA.
 * Ela utiliza um "juiz" (ToneJudge) para determinar se a resposta é profissional e adequada.
 * Se a resposta for considerada rude ou informal, o guardrail solicita uma reescrita mantendo a polidez e formalidade.
 */
@ApplicationScoped
public class ToneGuardrail implements OutputGuardrail {
    @Inject
    ToneJudge judge; // Injetamos nosso "Juiz"

    @Override
    public OutputGuardrailResult validate(AiMessage aiMessage) {
        if (!judge.isProfessional(aiMessage.text())) {
            return reprompt(aiMessage.text(),""" 
                    Sua resposta foi detectada como rude ou informal demais. 
                    "Reescreva-a mantendo a polidez e formalidade de um agente de viagens sênior. 
            """);
        }
        return OutputGuardrailResult.success();
    }
}