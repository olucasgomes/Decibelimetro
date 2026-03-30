# SoundLimit (Saúde Auditiva em Ambientes de Trabalho)

**Problema:** Exposição prolongada a ruídos altos em ambientes urbanos ou industriais sem percepção do risco real.

**Sensor Principal:** Microfone (Sensor de Áudio Nativo).

**Funcionalidade Principal:** O app mede o nível de decibéis do ambiente em tempo real. Implementa um algoritmo de persistência que dispara um alerta educativo e de segurança apenas se o ruído exceder 85 dB por mais de 30 segundos contínuos (evitando picos falsos).

**Interface (Material Design):**
- Medidor central (Gauge) com feedback visual cromático.
- Cards de status dinâmicos.
- Uso de Bottom Sheets (M3) para alertas e informações da NR-15.
- Dark Theme para melhor usabilidade em campo.
