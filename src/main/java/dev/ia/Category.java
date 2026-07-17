package dev.ia;

public enum Category {
    ADVENTURE("Aventura"),
    BEACH("Praia"),
    CULTURAL("Cultural"),
    CRUISE("Cruzeiro"),
    FAMILY("Familiar"),
    LUXURY("Luxo"),
    NATURE("Natureza"),
    ROMANTIC("Romântico"),
    SHOPPING("Compras"),
    SKIING("Esqui"),
    WELLNESS("Bem-estar");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
