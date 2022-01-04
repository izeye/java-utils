/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izeye.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilities for fixing Micrometer license headers.
 *
 * @author Johnny Lim
 */
public class MicrometerLicenseHeaderFix {

	public void fix(String path) throws IOException {
		File directory = new File(path);
		File[] files = directory.listFiles();
		for (File file : files) {
			String absolutePath = file.getAbsolutePath();
			if (file.isDirectory()) {
				if (absolutePath.contains("/build/")) {
					continue;
				}
				fix(absolutePath);
			}
			else {
				if (absolutePath.endsWith(".java")) {
					System.out.println("Fixing " + absolutePath + "...");

					Path sourcePath = Paths.get(absolutePath);
					List<String> lines = Files.lines(sourcePath).collect(Collectors.toList());
					lines.set(0, lines.get(0).replace("**", "*"));
					Stream.of(2, 6, 8).forEach((i) -> lines.set(i, lines.get(i).replace(" <p>", "")));
					String fixed = String.join(System.lineSeparator(), lines) + System.lineSeparator();
					Files.writeString(sourcePath, fixed);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String projectPath = "/Users/user/IdeaProjects/micrometer";
		new MicrometerLicenseHeaderFix().fix(projectPath);
	}

}
