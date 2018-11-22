package com.hdfs.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HDFSCase {
	private static Logger logger = Logger.getLogger(HDFSCase.class);
	private Configuration conf = new Configuration();
	
	@BeforeAll
	static void initAll() {
	}
	@BeforeEach
	void init() {
		conf.set("fs.defaultFS","hdfs://hadoop-01:9000");
	}
	@AfterEach
	void tearDown() {
	
	}
	
	@AfterAll
	static void tearDownAll() {
	}
	/**
	 * 获取HDFS系统
	 */
	@Test
	public void testGetHdfs(){
		try {
			FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-01:9000"), conf);
			System.out.println(fs);
			fs.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将文件上传到hdfs
	 */
	@Test
	public void testUploadFileToHDFS(){
		try {
			FileSystem fs = FileSystem.get(conf);
			Path src = new Path("file:///D://app//jdk.tar.gz");
			Path dst = new Path("/tmp/datatmp");
			fs.copyFromLocalFile(src,dst);
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除HDFS上单个文件
	 */
	@Test
	public void deleteHDFSFile(){
		try {
			FileSystem fs = FileSystem.get(conf);
			Path dst = new Path("/tmp/datatmp/jdk.tar.gz");
			fs.deleteOnExit(dst);
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 下载文件
	 */
	@Test
	public void testDownLoad(){
		try {
			FileSystem fileSystem = FileSystem.get(conf);
			
			Path src = new Path("/tmp/datatmp");
			Path dst = new Path("file:///D://app//jdk1.tar.gz");
			fileSystem.copyToLocalFile(src,dst);
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建目录
	 */
	@Test
	public void testMkdir(){
		FileSystem fileSystem = null;
		try {
			fileSystem = FileSystem.get(conf);
			boolean isMkdir = fileSystem.mkdirs(new Path("/tmp/apidir"));
			System.out.println(isMkdir);
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 更改文件名
	 */
	@Test
	public void testRename(){
		FileSystem fileSystem = null;
		try {
			fileSystem = FileSystem.get(conf);
			Path path1 = new Path("/tmp/datatmp/jdk.tar.gz");
			Path path2 = new Path("/tmp/datatmp/jdk2.tar.gz");
			boolean isRename = fileSystem.rename(path1, path2);
			System.out.println(isRename);
			
			
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 查看文件详情
	 */
	@Test
	public void testCatFile(){
		FileSystem fileSystem = null;
		try {
			fileSystem = FileSystem.get(conf);
		
			Path path2 = new Path("/tmp/datatmp");
			for (RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(path2, true);iterator.hasNext();){
				LocatedFileStatus fileStatus = iterator.next();
				
				System.out.println("BlockSize:"+fileStatus.getBlockSize());
				System.out.println("getLen:"+fileStatus.getLen());
				System.out.println("Owner:"+fileStatus.getOwner());
				System.out.println("permission:"+fileStatus.getPermission());
/*			System.out.println("getReplication:"+fileStatus.getReplication());
			System.out.println("getSymlink:"+fileStatus.getSymlink());*/
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date1 = new Date();
				date1.setTime(fileStatus.getAccessTime());
				System.out.println("getAccessTime:"+sdf.format(date1));
				
				Date date2 = new Date();
				date2.setTime(fileStatus.getModificationTime());
				System.out.println("getModificationTime:"+sdf.format(date2));
				
				System.out.println(fileStatus.toString());
				
				BlockLocation[] blockLocations = fileStatus.getBlockLocations();
				for(int i=0;i<blockLocations.length;i++){
					System.out.println("getNames:"+blockLocations[i].getNames().toString());
					System.out.println("getHosts:"+blockLocations[i].getHosts().toString());
					System.out.println("getLength:"+blockLocations[i].getLength());
					System.out.println("getTopologyPaths:"+blockLocations[i].getTopologyPaths().toString());
					
				}
			}
			
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void getFileStatus(FileSystem fileSystem,Path path1) throws IOException {
		FileStatus[] fileStatuses = fileSystem.listStatus(path1);
		
		for(int i=0;i<fileStatuses.length;i++){
			FileStatus fileStatus = fileStatuses[i];
			if(fileStatus.isFile()){
				System.out.println("File:"+fileStatus.getPath());
			}else {
				System.out.println("Directory:"+fileStatus.getPath());
				getFileStatus(fileSystem,fileStatus.getPath());
			}
		}
		
	}
	
	
	/**
	 * 列出所有的文件以及文件夹
	 */
	@Test
	public void testListFileAndDirect(){
		FileSystem fileSystem = null;
		try {
			fileSystem = FileSystem.get(conf);
			Path path1 = new Path("/user/hive/warehouse/par_dnm");
			getFileStatus(fileSystem,path1);
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 通过IO流上传文件
	 */
	@Test
	public void testIOUPLoad(){
		FileSystem fileSystem = null;
		FileInputStream fis = null ;
		FSDataOutputStream fsDataOutputStream  = null ;
		try {
			fileSystem = FileSystem.get(conf);
			
			fis = new FileInputStream("D://app//jdk.tar.gz");
			
			Path path = new Path("/tmp/apidir/jdk.tar.gz");
			
			fsDataOutputStream = fileSystem.create(path);
			
			IOUtils.copyBytes(fis,fsDataOutputStream,4096);
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeStream(fis);
			IOUtils.closeStream(fsDataOutputStream);
		}
		
	}
	/**
	 * 通过IO流下载
	 */
	@Test
	public void testIODownLoad(){
		FileSystem fileSystem = null;
		FileOutputStream fos = null ;
		FSDataInputStream inStream = null ;
		try {
			fileSystem = FileSystem.get(conf);
			
			fos = new FileOutputStream("D://app//jdk1.tar.gz");
			
			Path path = new Path("/tmp/apidir/jdk.tar.gz");
			
			inStream = fileSystem.open(path);
			
			IOUtils.copyBytes(inStream,fos,4096);
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeStream(fos);
			IOUtils.closeStream(inStream);
		}
		
	}
	
	/**
	 * 通过 定位块下载文件
	 */
	@Test
	public void testBlockDownLoad(){
		FileSystem fileSystem = null;
		FileOutputStream fos = null ;
		FSDataInputStream inStream = null ;
		try {
			fileSystem = FileSystem.get(conf);
			Path path = new Path("/tmp/apidir/jdk.tar.gz");
			inStream = fileSystem.open(path);
			RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(path, true);
			while (iterator.hasNext()){
				LocatedFileStatus next = iterator.next();
				if(next.isFile()){
					BlockLocation[] blockLocations = next.getBlockLocations();
					for(int i=0;i<blockLocations.length;i++){
						long offset = blockLocations[i].getOffset();
						long length = blockLocations[i].getLength();
						inStream.seek(offset);
						fos = new FileOutputStream("D://app/jdk.tar.gz.part"+i);
						IOUtils.copyBytes(inStream, fos, length, false);
					}
				}
			}
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeStream(fos);
			IOUtils.closeStream(inStream);
		}
		
	}
	public volatile int inc = 0;
	
	public void increase() {
		inc++;
	}
	/**
	 * 通过 定位块下载文件
	 */
	@Test
	public void testVolatile(){
		for(int i=0;i<10;i++){
			new Thread(){
				public void run() {
					for(int j=0;j<1000;j++)
						increase();
				};
			}.start();
		}
		
		while(Thread.activeCount()>1)  //保证前面的线程都执行完
			Thread.yield();
		System.out.println(inc);
	}
	
}
