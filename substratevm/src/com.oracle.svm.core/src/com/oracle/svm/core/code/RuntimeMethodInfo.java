/*
 * Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.svm.core.code;

import org.graalvm.nativeimage.c.function.CodePointer;
import org.graalvm.nativeimage.c.struct.RawField;
import org.graalvm.nativeimage.c.struct.RawStructure;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;

import com.oracle.svm.core.c.NonmovableArray;
import com.oracle.svm.core.c.NonmovableObjectArray;

import jdk.vm.ci.code.InstalledCode;

@RawStructure
public interface RuntimeMethodInfo extends PointerBase {
    /**
     * The object "fields" of this class, managed as an array for simplicity.
     *
     * [0] String: The {@linkplain InstalledCode#getName() name of the InstalledCode}. Stored here
     * so it remains available even after the code is no longer available. Note that the String is
     * not pinned, so this field must not be accessed during garbage collection.
     *
     * [1] WeakReference<SubstrateInstalledCode>: The handle to the compiled code for the outside
     * world. We only have a weak reference to it, to avoid keeping code alive. Note that the both
     * the InstalledCode and the weak reference are not pinned, so this field must not be accessed
     * during garbage collection.
     *
     * [2] InstalledCodeObserverHandle[]: observers for installation and removal of this code.
     */
    @RawField
    void setObjectFields(NonmovableObjectArray<?> fields);

    /** @see #setObjectFields */
    @RawField
    NonmovableObjectArray<?> getObjectFields();

    @RawField
    int getTier();

    @RawField
    void setTier(int tier);

    @RawField
    CodePointer getCodeStart();

    @RawField
    UnsignedWord getCodeSize();

    @RawField
    NonmovableArray<Byte> getReferenceMapEncoding();

    @RawField
    void setCodeStart(CodePointer codeStart);

    @RawField
    void setCodeSize(UnsignedWord codeSize);

    @RawField
    NonmovableArray<Byte> getCodeInfoIndex();

    @RawField
    void setCodeInfoIndex(NonmovableArray<Byte> codeInfoIndex);

    @RawField
    NonmovableArray<Byte> getCodeInfoEncodings();

    @RawField
    void setCodeInfoEncodings(NonmovableArray<Byte> codeInfoEncodings);

    @RawField
    void setReferenceMapEncoding(NonmovableArray<Byte> referenceMapEncoding);

    @RawField
    NonmovableArray<Byte> getFrameInfoEncodings();

    @RawField
    void setFrameInfoEncodings(NonmovableArray<Byte> frameInfoEncodings);

    @RawField
    NonmovableObjectArray<Object> getFrameInfoObjectConstants();

    @RawField
    void setFrameInfoObjectConstants(NonmovableObjectArray<Object> frameInfoObjectConstants);

    @RawField
    NonmovableObjectArray<Class<?>> getFrameInfoSourceClasses();

    @RawField
    void setFrameInfoSourceClasses(NonmovableObjectArray<Class<?>> frameInfoSourceClasses);

    @RawField
    NonmovableObjectArray<String> getFrameInfoSourceMethodNames();

    @RawField
    void setFrameInfoSourceMethodNames(NonmovableObjectArray<String> frameInfoSourceMethodNames);

    @RawField
    NonmovableObjectArray<String> getFrameInfoNames();

    @RawField
    void setFrameInfoNames(NonmovableObjectArray<String> frameInfoNames);

    @RawField
    boolean getCodeConstantsLive();

    @RawField
    void setCodeConstantsLive(boolean codeConstantsLive);

    @RawField
    NonmovableArray<Byte> getObjectsReferenceMapEncoding();

    @RawField
    void setObjectsReferenceMapEncoding(NonmovableArray<Byte> objectsReferenceMapEncoding);

    @RawField
    long getObjectsReferenceMapIndex();

    @RawField
    void setObjectsReferenceMapIndex(long objectsReferenceMapIndex);

    @RawField
    NonmovableArray<Integer> getDeoptimizationStartOffsets();

    @RawField
    void setDeoptimizationStartOffsets(NonmovableArray<Integer> deoptimizationStartOffsets);

    @RawField
    NonmovableArray<Byte> getDeoptimizationEncodings();

    @RawField
    void setDeoptimizationEncodings(NonmovableArray<Byte> deoptimizationEncodings);

    @RawField
    NonmovableObjectArray<Object> getDeoptimizationObjectConstants();

    @RawField
    void setDeoptimizationObjectConstants(NonmovableObjectArray<Object> deoptimizationObjectConstants);
}
