package metida;

import metida.interfacable.IUserStrategy;
import org.joor.Reflect;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;

@Component
public class StrategyCreate {

    public void strCreate() {
        IUserStrategy userStrategy;
        userStrategy = Reflect.compile(
                "com.example.CompileTest",
                "package com.example;\n" +
                        "import metida.factory.TankFactory; \n" +
                        "import metida.providers.TankFactoryProvider; \n" +
                        "class CompileTest implements metida.interfacable.IUserStrategy {" +
                        "   public void init() {" +
                        "  }\n" +
                        "}\n").create().get();

        userStrategy.init();

    }

    static public void main(String arg[]) {
        File folder = new File("C://");
        try {
            processFilesFromFolder(folder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void processFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry);
                continue;
            } else {
                final String[] mask = {".class"};
                String[] files = folder.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File folder, String name) {
                        for (String s : mask)
                            if (name.toLowerCase().endsWith(s)) return true;
                        return false;
                    }
                });

                for (String fileName : files)
                    System.out.println("File: " + fileName);
            }
        }
    }
}