/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.or.io.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * 
 * @author Jingqi Xu
 */
public class RamdomAccessFileInputStream extends InputStream {
	//
	private final RandomAccessFile file;
	
	/**
	 * 
	 */
	public RamdomAccessFileInputStream(RandomAccessFile file) {
		this.file = file;
	}

	/**
	 * 
	 */
	@Override
	public int available() throws IOException {
		final long fp = this.file.getFilePointer();
		return (int)(this.file.length() - fp);
	}
	
	@Override
	public void close() throws IOException {
		this.file.close();
	}
	
	@Override
	public long skip(long n) throws IOException {
		final long fp = this.file.getFilePointer();
		this.file.seek(fp + n);
		return n;
	}
	 
	@Override
	public int read() throws IOException {
		return this.file.read();
	}

	@Override
	public int read(byte b[], int off, int len) throws IOException {
		return this.file.read(b, off, len);
	}
}