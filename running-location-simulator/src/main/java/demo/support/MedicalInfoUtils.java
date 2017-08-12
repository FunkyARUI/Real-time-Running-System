/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package demo.support;

import demo.model.MedicalInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility to provide random {@link MedicalInfo}s.
 */
public class MedicalInfoUtils {

    /**
     * Private Constructor.
     * Suppress default constructor for non-instantiability
     */
    private MedicalInfoUtils() {
        throw new AssertionError();
    }

    public static final List<MedicalInfo> MEDICAL_INFOS = new ArrayList<>(5);

    static {
        final MedicalInfo medicalInfo1 = new MedicalInfo();
        medicalInfo1.setBandMake("Fitbit");
        medicalInfo1.setMedCode("LTP");
        medicalInfo1.setMedicalInfoId("Fitbit");
        medicalInfo1.setMedicalInfoClassification("SupplyInfo");
        medicalInfo1.setDescription("Low Blood Sugar Warning");
        medicalInfo1.setAidInstructions("Take energy as soon as possible");
        medicalInfo1.setFmi("14");
        medicalInfo1.setBfr("3696");

        final MedicalInfo medicalInfo2 = new MedicalInfo();
        medicalInfo2.setBandMake("Fitbit");
        medicalInfo2.setMedCode("MJR");
        medicalInfo2.setMedicalInfoId("Fitbit");
        medicalInfo2.setMedicalInfoClassification("SupplySoon");
        medicalInfo2.setDescription("Band Failure");
        medicalInfo2.setAidInstructions("Repair at store");
        medicalInfo2.setFmi("31");
        medicalInfo2.setBfr("3719");

        final MedicalInfo medicalInfo3 = new MedicalInfo();
        medicalInfo3.setBandMake("Fitbit");
        medicalInfo3.setMedCode("FMW");
        medicalInfo3.setMedicalInfoId("Fitbit");
        medicalInfo3.setMedicalInfoClassification("SupplyInfo");
        medicalInfo3.setDescription("Energy Drink Required");
        medicalInfo3.setAidInstructions("Check energy level before taking energy drink");
        medicalInfo3.setFmi("14");
        medicalInfo3.setBfr("171");

        final MedicalInfo medicalInfo4 = new MedicalInfo();
        medicalInfo4.setBandMake("Fitbit");
        medicalInfo4.setMedCode("OHT");
        medicalInfo4.setMedicalInfoId("Fitbit");
        medicalInfo4.setMedicalInfoClassification("SupplySoon");
        medicalInfo4.setDescription("Overheating Warning");
        medicalInfo4.setAidInstructions("Cool it down");
        medicalInfo4.setFmi("15");
        medicalInfo4.setBfr("3719");

        final MedicalInfo medicalInfo5 = new MedicalInfo();
        medicalInfo5.setBandMake("Fitbit");
        medicalInfo5.setMedCode("LOP");
        medicalInfo5.setMedicalInfoId("Fitbit");
        medicalInfo5.setMedicalInfoClassification("StopRunning");
        medicalInfo5.setDescription("Low Blood Sugar Warning");
        medicalInfo5.setAidInstructions("Take energy as soon as possible");
        medicalInfo5.setFmi("7");
        medicalInfo5.setBfr("4334");

        MEDICAL_INFOS.add(medicalInfo1);
        MEDICAL_INFOS.add(medicalInfo2);
        MEDICAL_INFOS.add(medicalInfo3);
        MEDICAL_INFOS.add(medicalInfo4);
        MEDICAL_INFOS.add(medicalInfo5);

    }

    ;

    public static MedicalInfo getRandomMedicalInfo() {
        return MEDICAL_INFOS.get(ThreadLocalRandom.current().nextInt(MEDICAL_INFOS.size()));
    }

}
