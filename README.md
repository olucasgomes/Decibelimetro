# SoundLimit - Decibelímetro Educativo (UNIFOR) 📱🔊

Este é um projeto desenvolvido para a disciplina **N700 - Desenvolvimento para Plataformas Móveis** da Universidade de Fortaleza (UNIFOR). O aplicativo utiliza o microfone nativo do dispositivo para medir o nível de ruído em decibéis (dB), com o objetivo de prevenir a perda auditiva em ambientes ruidosos.

---

## 👥 Grupo 35 - Equipe de Desenvolvimento

| Nome Completo | Matrícula | E-mail | GitHub |
| :--- | :--- | :--- | :--- |
| Marcos Daniel Gomes Dantas | 2425179 | contatomarcosdgomes@gmail.com | mdanieldantas |
| Kaio Bruno Soares dos Santos | 2425109 | Kaiobruno@edu.unifor.br | DevKaioBrunu |
| Raphael Araripe Magalhães | 2425278 | raphael_araripe@hotmail.com | Hiwls |
| Pedro Yuri Rodrigues Nunes | 2425031 | pedroyurirn@edu.unifor.br | PYEDRO |
| Erico Alves de Lima | 2323762 | ericoallima@gmail.com | EricoAlves07 |
| Manoel Lucas Gomes Dantas | 2217137 | gomes@edu.unifor.br | olucasgomes |

---

## 🛠️ Requisitos Técnicos
- **Ambiente:** Android Studio Koala ou superior
- **Linguagem:** Java (Nativo)
- **SDK Alvo:** 34 (Android 14)
- **JDK:** 17
- **Material Design:** Componentes 3 (M3)

---

## 🚀 Funcionalidades e Requisitos (NR-15)
Conforme os requisitos da disciplina, o app contempla:
- [x] **Sensor Nativo:** Acesso ao microfone via `MediaRecorder`.
- [x] **UX/UI:** Interface baseada em Material Design com feedback visual de risco.
- [x] **Segurança:** Alerta automático (Bottom Sheet) ao ultrapassar **85 dB** (Limite NR-15).
- [x] **Permissões:** Gestão de permissões em tempo de execução.
- [x] **Ciclo de Vida:** Interrupção do sensor quando o app está em segundo plano.

---

## 📋 Checklist de Entrega (To-Do)
Baseado nas orientações do professor:
- [x] **Desenvolvimento:** Aplicativo funcional e testado.
- [x] **Documentação Técnica:** Gerada em Markdown na pasta `/Docs_Trab`.
- [ ] **Prototipação:** Inserir link do Figma/Canva no relatório final.
- [ ] **Relatório PDF:** Converter o `Relatorio_Tecnico.md` para PDF.
- [ ] **Prints:** Adicionar capturas de tela do app no relatório.
- [ ] **Envio:** Submeter o arquivo PDF + Link do GitHub no portal.

**Prazo de Entrega:** 30/03/2026

---

## ⚙️ Como Executar
1. Clone este repositório ou extraia o arquivo ZIP.
2. Abra a pasta no **Android Studio**.
3. Sincronize o **Gradle** e aguarde o build.
4. Conecte um dispositivo físico ou utilize um emulador (API 31+ recomendado).
5. Certifique-se de conceder a permissão de áudio ao iniciar.

---
*Observação: Os valores apresentados são estimativas baseadas na amplitude capturada pelo hardware do dispositivo.*