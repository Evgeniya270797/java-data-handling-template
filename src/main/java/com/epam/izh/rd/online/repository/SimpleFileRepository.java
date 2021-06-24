package com.epam.izh.rd.online.repository;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {




        File dir =  new File(path);

        long count = 0;

        if(dir.isFile()){
            count++;
        }else{
            File[] directories = dir.listFiles();
            if (directories!=null){
                for (File file: directories) {
                    count += countDirsInDirectory(file.getAbsolutePath());
                }
            }
        }



        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File dirs =  new File(path);

        long count = 0;

        if(dirs.isDirectory()){
            count++;
        }else{
            File[] directories = dirs.listFiles();
            if (directories!=null){
                for (File file: directories) {
                    count += countDirsInDirectory(file.getAbsolutePath());
                }
            }
        }



        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File dirs = new File(from);
        File[] files = dirs.listFiles();
        if(files!= null){
            for (File file:files) {
                if(file.isFile()){
                    try{
                        Files.copy(file.toPath(),Paths.get(to + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File file = new File(path + File.separator + name + ".txt");
        boolean result = false;
        if(!file.exists()){
         try{
             Files.write(file.toPath(),new byte[0]);
             result= true;
         } catch (IOException e) {
             e.printStackTrace();
         }
        }

        return result;


    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String content="";

        Path path = Paths.get("src"+  File.separator +"main"+ File.separator +"resources"+ File.separator + fileName);

        try {
             content = Files.lines(path).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }
}
