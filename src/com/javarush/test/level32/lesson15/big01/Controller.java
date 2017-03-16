package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by 1 on 15.01.2017.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void exit()
    {
        System.exit(0);
    }

    public void init()
    {
        createNewDocument();
    }

    public Controller(View view)
    {
        this.view = view;
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    public void resetDocument()
    {
        if (document != null)
            document.removeUndoableEditListener(view.getUndoListener());

        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text)
    {
        resetDocument();
        StringReader reader = new StringReader(text);
        try
        {
            new HTMLEditorKit().read(reader, document, 0);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        catch (BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        StringWriter writer = new StringWriter();
        try
        {
            new HTMLEditorKit().write(writer, document, 0, document.getLength());
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        catch (BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void createNewDocument()
    {
        //Выбирать html вкладку у представления
        view.selectHtmlTab();
        //Сбрасывать текущий документ
        resetDocument();
        //Устанавливать новый заголовок окна
        view.setTitle("HTML редактор");
        //Сбрасывать правки в Undo менеджере
        view.resetUndo();
        //Обнулить переменную currentFile
        currentFile = null;
    }

    public void openDocument()
    {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION)
        {
            currentFile = chooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader reader = new FileReader(currentFile))
            {
                new HTMLEditorKit().read(reader, document, 0);
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }

    }

    public void saveDocument()
    {
        if (currentFile != null)
        {
            view.selectHtmlTab();
            try (FileWriter writer = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
        else
            saveDocumentAs();
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION)
        {
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter writer = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }
}

