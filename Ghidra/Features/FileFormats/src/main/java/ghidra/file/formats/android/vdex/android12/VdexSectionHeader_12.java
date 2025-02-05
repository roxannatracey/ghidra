/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.file.formats.android.vdex.android12;

import java.io.IOException;

import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.StructConverter;
import ghidra.program.model.data.*;
import ghidra.util.exception.DuplicateNameException;

/**
 * https://android.googlesource.com/platform/art/+/refs/heads/android-s-beta-5/runtime/vdex_file.h#92
 * 
 * https://android.googlesource.com/platform/art/+/refs/heads/android12-release/runtime/vdex_file.h#92
 * 
 * https://android.googlesource.com/platform/art/+/refs/heads/android13-release/runtime/vdex_file.h#92
 */
public class VdexSectionHeader_12 implements StructConverter {

	private VdexSection_12 section_kind;
	private int section_offset;
	private int section_size;

	public VdexSectionHeader_12(BinaryReader reader) throws IOException {
		section_kind = VdexSection_12.values()[reader.readNextInt()];
		section_offset = reader.readNextInt();
		section_size = reader.readNextInt();
	}

	public VdexSection_12 getSectionKind() {
		return section_kind;
	}

	public int getSectionOffset() {
		return section_offset;
	}

	public int getSectionSize() {
		return section_size;
	}

	@Override
	public DataType toDataType() throws DuplicateNameException, IOException {
		String className = VdexSectionHeader_12.class.getSimpleName();
		Structure structure = new StructureDataType(className, 0);
		structure.add(DWORD, "section_kind", null);
		structure.add(DWORD, "section_offset", null);
		structure.add(DWORD, "section_size", null);
		return structure;
	}

}
