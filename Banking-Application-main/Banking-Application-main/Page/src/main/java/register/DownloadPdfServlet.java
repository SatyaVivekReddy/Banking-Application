package register;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import register.Transaction;

@WebServlet("/register/DownloadPdfServlet")
public class DownloadPdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");

        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();
            document.add(new Paragraph("Transaction History"));

            for (Transaction transaction : transactions) {
                document.add(new Paragraph("Account Number: " + transaction.getAccountNumber()));
                document.add(new Paragraph("Amount: " + transaction.getAmount()));
                document.add(new Paragraph("Transaction Type: " + transaction.getTransactionType()));
                document.add(new Paragraph("Transaction Time: " + transaction.getTransactionTime()));
                document.add(new Paragraph("")); // Add an empty line between transactions
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
