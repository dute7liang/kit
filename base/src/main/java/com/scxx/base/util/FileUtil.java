package com.scxx.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


import com.scxx.base.exception.ScxxException;
import com.scxx.base.exception.ScxxExceptionEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * NIO way
     */
    public static byte[] toByteArray(String filename) {

        File f = new File(filename);
        if (!f.exists()) {
            log.error("文件未找到！" + filename);
            throw new ScxxException(ScxxExceptionEnum.FILE_NOT_FOUND);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            throw new ScxxException(ScxxExceptionEnum.FILE_READING_ERROR);
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                throw new ScxxException(ScxxExceptionEnum.FILE_READING_ERROR);
            }
            try {
                fs.close();
            } catch (IOException e) {
                throw new ScxxException(ScxxExceptionEnum.FILE_READING_ERROR);
            }
        }
    }

    /**
     * 删除目录
     *
     * @author fengshuonan
     * @Date 2017/10/30 下午4:15
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void delDir(String dirPath) throws IOException {
        log.info("删除文件开始:{}.",dirPath);
        long start = System.currentTimeMillis();
        try{
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                return;
            }
            if (dirFile.isFile()) {
                dirFile.delete();
                return;
            }
            File[] files = dirFile.listFiles();
            if(files==null){
                return;
            }
            for (int i = 0; i < files.length; i++) {
                delDir(files[i].getAbsolutePath());
            }
            dirFile.delete();
            log.info("删除文件:{}. 耗时:{}ms. ",dirPath,System.currentTimeMillis()-start);
        }catch(Exception e){
            log.info("删除文件:{}. 异常:{}. 耗时:{}ms. ",dirPath,e,System.currentTimeMillis()-start);
            throw new RuntimeException("删除文件异常.");
        }
    }
}