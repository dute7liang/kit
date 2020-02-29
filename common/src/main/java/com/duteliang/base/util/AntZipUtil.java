package com.duteliang.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ant解压缩文件。支持中文
 * @author: zl
 * @Date: 2019-10-23 14:48
 */
@Slf4j
public class AntZipUtil {

    public static void main(String[] args) throws IOException {
//        zip("C:\\Users\\zhangliang\\Desktop\\新建文件夹\\劳资是中文", "C:\\Users\\zhangliang\\Desktop\\新建文件夹\\我是中文.zip");
//        toZip("C:\\Users\\zhangliang\\Desktop\\新建文件夹\\劳资是中文", "C:\\Users\\zhangliang\\Desktop\\新建文件夹\\我是中文2.zip",false);
//        zip("C:\\Users\\zhangliang\\Desktop\\新建文件夹\\222.txt", "C:\\Users\\zhangliang\\Desktop\\新建文件夹\\yingwen.zip");
//        unzip("C:\\Users\\zhangliang\\Desktop\\新建文件夹\\我是中文.zip","C:\\Users\\zhangliang\\Desktop\\新建文件夹\\dshjk\\");

        List<File> fileList = new ArrayList<>();
        fileList.add(new File("C:\\Users\\zhangliang\\Desktop\\新建文件夹\\222.txt"));
        fileList.add(new File("C:\\Users\\zhangliang\\Desktop\\新建文件夹\\dshjk\\Snipaste_2019-10-16_11-17-02.png"));
        toZip(fileList,"C:\\Users\\zhangliang\\Desktop\\新建文件夹\\我是中文2.zip");
    }


    /**
     * 缓冲大小
     */
    private static int BUFFER_SIZE = 2 << 10;

    /**
     * 压缩成ZIP
     * @param srcFiles 需要压缩的文件列表
     * @param zipPath  zip文件路径
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles , String zipPath) {
        long start = System.currentTimeMillis();
        ZipArchiveOutputStream zos = null ;
        try {
            File zipFile = new File(zipPath);
            zos = new ZipArchiveOutputStream(zipFile);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putArchiveEntry(new ZipArchiveEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeArchiveEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            log.info("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.finish();
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void toZip(String srcDir,String zipPath,boolean isDelSrcFile) {
        long start = System.currentTimeMillis();
        ZipArchiveOutputStream zaos = null;
        try {
            File sourceFile = new File(srcDir);
            File zipFile = new File(zipPath);
            if(!sourceFile.exists()){
                throw new RuntimeException("需压缩文件或者文件夹不存在");
            }
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            compress(sourceFile, zaos, sourceFile.getName());
            if(isDelSrcFile){
                FileUtil.delDir(srcDir);
            }
            log.info("原文件:{}. 压缩到:{}完成. 是否删除原文件:{}. 耗时:{}ms. ",srcDir,zipPath,isDelSrcFile,System.currentTimeMillis()-start);
        } catch (Exception e) {
            log.error("zip error from AntZipUtil: {}. ",e.getMessage());
            throw new RuntimeException("zip error from AntZipUtil",e);
        } finally {
            try {
                if (zaos != null) {zaos.finish();zaos.close();}
            } catch (Exception e) {}
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zaos zip输出流
     * @param name 压缩后的名称
     */
    private static void compress(File sourceFile, ZipArchiveOutputStream zaos, String name)
            throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            zaos.putArchiveEntry(new ZipArchiveEntry(sourceFile,name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zaos.write(buf, 0, len);
            }
            zaos.closeArchiveEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                zaos.putArchiveEntry(new ZipArchiveEntry(sourceFile,name + "/"));
                zaos.closeArchiveEntry();
            } else {
                for (File file : listFiles) {
                    compress(file, zaos, name + "/" + file.getName());
                }
            }
        }
    }





    /**
     * 把zip文件解压到指定的文件夹
     * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip"
     * @param saveFileDir 解压后的文件存放路径, 如"D:/test/" ()
     */
    public static void unzip(String zipFilePath, String saveFileDir) {
        if(!saveFileDir.endsWith("\\") && !saveFileDir.endsWith("/") ){
            saveFileDir += File.separator;
        }
        File dir = new File(saveFileDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(zipFilePath);
        if (file.exists()) {
            InputStream is = null;
            ZipArchiveInputStream zais = null;
            try {
                is = new FileInputStream(file);
                zais = new ZipArchiveInputStream(is);
                ArchiveEntry archiveEntry = null;
                while ((archiveEntry = zais.getNextEntry()) != null) {
                    // 获取文件名
                    String entryFileName = archiveEntry.getName();
                    // 构造解压出来的文件存放路径
                    String entryFilePath = saveFileDir + entryFileName;
                    OutputStream os = null;
                    try {
                        // 把解压出来的文件写到指定路径
                        File entryFile = new File(entryFilePath);
                        if(entryFileName.endsWith("/")){
                            entryFile.mkdirs();
                        }else{
                            os = new BufferedOutputStream(new FileOutputStream(
                                    entryFile));
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while((len = zais.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                        }
                    } catch (IOException e) {
                        throw new IOException(e);
                    } finally {
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                    }

                }
            } catch (Exception e) {
                log.error("解压文件异常！",e);
                throw new RuntimeException(e);
            } finally {
                try {
                    if (zais != null) {
                        zais.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    // nothing
                }
            }
        }
    }

}
