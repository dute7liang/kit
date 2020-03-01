package com.duteliang.base.util;

import com.duteliang.base.exception.KitException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * java原生的压缩方式。
 * 不支持文件名中文。经过测试稳定性比较好
 * @author: zl
 * @Date: 2019-10-12 10:25
 */
@Slf4j
public class ZipUtil {

    /**
     * 缓冲大小
     */
    private static int BUFFER_SIZE = 2 << 10;

    /**
     * 是否保留原来的目录结构
     * true:  保留目录结构;
     * false: 所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    private static final boolean KeepDirStructure = true;

    /**
     * zip解压
     * @param srcFile        zip源文件
     * @param destDirPath     解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public static void unZip(File srcFile, String destDirPath) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new FileNotFoundException("文件不存在！");
        }
        // 开始解压
        ZipFile zipFile = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                log.info("解压 {}",entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    is = zipFile.getInputStream(entry);
                    fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            log.info("解压完成，耗时：{} ms",end - start);
        } catch (Exception e) {
            throw new RuntimeException("文件解压失败！",e);
        } finally {
            try {
                if(fos != null){
                    fos.close();
                }
                if(is != null){
                    is.close();
                }
                if(zipFile != null){
                    zipFile.close();
                }
            } catch (IOException e) {
                // do nothing
            }
        }
    }


    /**
     * 压缩文件
     * @param srcDir 解压路径
     * @param outPathFile  输出路径(带文件名)
     * @param isDelSrcFile  解压后 是否删除源路径文件
     * @return
     * @throws IOException
     */
    public static void toZip(String srcDir,String outPathFile,boolean isDelSrcFile) throws IOException {
        long start = System.currentTimeMillis();
        FileOutputStream out = null;
        ZipOutputStream zos = null;
        try {
            out = new FileOutputStream(new File(outPathFile));
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            if(!sourceFile.exists()){
                throw new FileNotFoundException("需压缩文件或者文件夹不存在");
            }
            compress(sourceFile, zos, sourceFile.getName());
            if(isDelSrcFile){
                FileUtil.delDir(srcDir);
            }
            log.info("原文件:{}. 压缩到:{}完成. 是否删除原文件:{}. 耗时:{}ms. ",srcDir,outPathFile,isDelSrcFile,System.currentTimeMillis()-start);
        } catch (Exception e) {
            log.error("zip error from ZipUtils: {}. ",e.getMessage());
            throw new IOException("zip error from ZipUtils");
        } finally {
            try {
                if (zos != null) {zos.close();}
                if (out != null) {out.close();}
            } catch (Exception e) {}
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos zip输出流
     * @param name 压缩后的名称
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name)
            throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                if (KeepDirStructure) {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    if (KeepDirStructure) {
                        compress(file, zos, name + "/" + file.getName());
                    } else {
                        compress(file, zos, file.getName());
                    }
                }
            }
        }
    }


}
