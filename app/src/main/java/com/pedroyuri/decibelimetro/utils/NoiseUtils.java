package com.pedroyuri.decibelimetro.utils;

public class NoiseUtils {
    public static String getStatus(int db) {
        if (db < 20) return "Muito Silencioso";
        if (db < 60) return "Moderado";
        if (db < 85) return "Alto";
        return "Perigoso";
    }

    public static String getDescription(int db) {
        if (db < 20) return "Sussurros, biblioteca.";
        if (db < 60) return "Trânsito leve, TV alta.";
        if (db < 85) return "Ambiente ruidoso.";
        return "Risco de dano auditivo imediato.";
    }
}
