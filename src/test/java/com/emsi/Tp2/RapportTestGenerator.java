package com.emsi.Tp2;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Générateur de rapport de tests
 * Exécute les tests et génère un rapport HTML
 */
public class RapportTestGenerator {

    public static void main(String[] args) {
        System.out.println("=== Génération du rapport de tests ===\n");

        // Create launcher discovery request
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        DiscoverySelectors.selectClass(PlanTestRechercheGoogle.class),
                        DiscoverySelectors.selectClass(TestLoginValide.class)
                )
                .build();

        // Create launcher and listener
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // Execute tests
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        // Get summary
        TestExecutionSummary summary = listener.getSummary();

        // Print summary to console
        printSummary(summary);

        // Generate HTML report
        try {
            generateHtmlReport(summary);
            System.out.println("\n✓ Rapport HTML généré : test-report.html");
        } catch (IOException e) {
            System.err.println("❌ Erreur lors de la génération du rapport : " + e.getMessage());
        }
    }

    private static void printSummary(TestExecutionSummary summary) {
        System.out.println("\n=== Résumé des tests ===");
        System.out.println("Tests lancés : " + summary.getTestsStartedCount());
        System.out.println("Tests réussis : " + summary.getTestsSucceededCount());
        System.out.println("Tests échoués : " + summary.getTestsFailedCount());
        System.out.println("Tests ignorés : " + summary.getTestsSkippedCount());

        if (summary.getTestsFailedCount() > 0) {
            System.out.println("\n❌ Échecs détectés :");
            summary.getFailures().forEach(failure -> {
                System.out.println("  - " + failure.getTestIdentifier().getDisplayName());
                System.out.println("    " + failure.getException().getMessage());
            });
        }
    }

    private static void generateHtmlReport(TestExecutionSummary summary) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try (PrintWriter writer = new PrintWriter(new FileWriter("test-report.html"))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang='fr'>");
            writer.println("<head>");
            writer.println("    <meta charset='UTF-8'>");
            writer.println("    <title>Rapport de Tests</title>");
            writer.println("    <style>");
            writer.println("        body { font-family: Arial, sans-serif; margin: 20px; }");
            writer.println("        h1 { color: #333; }");
            writer.println("        .summary { background-color: #f0f0f0; padding: 15px; border-radius: 5px; }");
            writer.println("        .success { color: green; font-weight: bold; }");
            writer.println("        .failure { color: red; font-weight: bold; }");
            writer.println("        .stats { margin: 10px 0; }");
            writer.println("        .test-item { margin: 10px 0; padding: 10px; border-left: 3px solid #ddd; }");
            writer.println("        .test-passed { border-left-color: green; background-color: #e8f5e9; }");
            writer.println("        .test-failed { border-left-color: red; background-color: #ffebee; }");
            writer.println("    </style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("    <h1>Rapport de Tests Automatisés</h1>");
            writer.println("    <p>Généré le : " + now.format(formatter) + "</p>");

            writer.println("    <div class='summary'>");
            writer.println("        <h2>Résumé</h2>");
            writer.println("        <div class='stats'>Tests lancés : <strong>" + summary.getTestsStartedCount() + "</strong></div>");
            writer.println("        <div class='stats'>Tests réussis : <span class='success'>" + summary.getTestsSucceededCount() + "</span></div>");
            writer.println("        <div class='stats'>Tests échoués : <span class='failure'>" + summary.getTestsFailedCount() + "</span></div>");
            writer.println("        <div class='stats'>Tests ignorés : " + summary.getTestsSkippedCount() + "</div>");
            writer.println("    </div>");

            if (summary.getTestsFailedCount() > 0) {
                writer.println("    <h2>Tests Échoués</h2>");
                summary.getFailures().forEach(failure -> {
                    writer.println("    <div class='test-item test-failed'>");
                    writer.println("        <strong>" + failure.getTestIdentifier().getDisplayName() + "</strong>");
                    writer.println("        <p>" + failure.getException().getMessage() + "</p>");
                    writer.println("    </div>");
                });
            }

            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
