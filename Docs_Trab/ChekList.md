# Checklist de Conformidade - Projeto SoundLimit 📱✅

Este checklist foi gerado com base nos requisitos da disciplina de **Desenvolvimento 
para Plataformas Móveis** para garantir a nota máxima (5,0 pts).

---

## 1. Identificação e Relevância (2,0 pts)
- [x] **Problema Real:** O relatório descreve claramente o risco de exposição a ruídos (PAIR) em ambientes industriais/urbanos?
- [x] **Originalidade:** A solução propõe algo além de um simples medidor (foco em saúde ocupacional e alertas educativos baseados na NR-15)?

## 2. Qualidade Técnica e Sensores (1,0 pt)
- [x] **Sensor Nativo:** O uso do microfone para captura de amplitude está implementado em `AudioMeterManager.java`.
- [x] **Ciclo de Vida:** O app interrompe a captura do microfone no `onStop()` para economizar bateria e garantir privacidade.
- [x] **Permissões:** O app solicita permissão de `RECORD_AUDIO` em tempo de execução (Runtime Permissions).
- [x] **Estabilidade:** O cálculo de dB é feito de forma assíncrona usando `Handler` para não travar a UI Thread.
- [x] **Inteligência de Dados:** Implementada lógica de persistência de 30 segundos para evitar alarmes falsos por ruídos de impacto.

## 3. Interface e UX/UI - Material Design (1,0 pt)
- [x] **Componentes Material:** O app utiliza `GaugeView` customizado, `MaterialButton` e `BottomSheetDialogFragment` (`HighNoiseDialog` e `NR15Dialog`).
- [ ] **Prototipação:** Tenho os prints ou o link do protótipo feito no Figma/Canva para incluir no relatório? (Pendente: Inserir link no documento final).
- [x] **Usabilidade:** A interface é intuitiva, possui Dark Theme e o alerta visual de 85dB está funcional.

## 4. Relatório Técnico (PDF) - OBRIGATÓRIO (1,0 pt)
O relatório em PDF deve conter:
- [x] **Capa:** Nome completo e matrícula de TODOS os integrantes inseridos no `Relatorio_Tecnico.md`.
- [x] **Introdução:** Definição do problema e justificativa social incluída.
- [x] **Requisitos Funcionais (RF):** Listados conforme a funcionalidade do app.
- [x] **Requisitos Não Funcionais (RNF):** Listados (Material Design, SDK 34, etc.).
- [x] **Arquitetura:** Descrição da organização do código e uso do sensor, incluindo a lógica de tempo de exposição.
- [ ] **Prints da Aplicação:** Capturas de tela do app funcionando. (Pendente: Alunos devem tirar os prints).
- [x] **Código Fonte Comentado:** Javadocs e comentários técnicos inseridos nas classes principais.
- [ ] **Link do GitHub:** Link público e funcional para o repositório. (Pendente: Inserir URL final).

## 5. Entrega Final (Logística)
- [ ] **Individualidade:** Todos os membros do grupo submeteram o PDF no portal?
- [ ] **Anexos:** O arquivo enviado contém o PDF + link do GitHub + APK para teste?
- [ ] **Prazo:** Entrega antes de 30/03/26.

---
### Notas de Progresso:
- [x] Implementação do alerta de segurança NR-15 com persistência de 30s.
- [x] Gestão de ciclo de vida para o sensor.
- [x] Documentação técnica atualizada com a nova lógica de exposição.
- [ ] Tirar print do alerta de 30s aparecendo no celular/emulador.
- [ ] Gerar APK final para entrega.
