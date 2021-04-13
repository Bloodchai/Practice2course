
package com.company;

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Jul 28 2018, 19:09 by COMSOL 5.2.1.262. */
public class Induction {
    private Model model = null;

    public Induction() {
        this.model = ModelUtil.create("Model");

        this.model.modelNode().create("comp1");

        this.model.geom().create("geom1", 3);

        this.model.mesh().create("mesh1", "geom1");

        this.model.physics().create("mf", "InductionCurrents", "geom1");

        this.model.study().create("std1");
        this.model.study("std1").create("stat", "Stationary");
        this.model.study("std1").feature("stat").activate("mf", true);

        this.model.param().set("R", "250[mm]");
        this.model.param().set("H", "2.5[cm]");
        this.model.param().set("Z1", "-0.0125");
        this.model.param().set("R1", "0.24837");
        this.model.param().set("R3", "100[cm]");
        this.model.param().set("H3", "100[cm]");
        this.model.param().set("Zplane3", "-50[cm]");
        this.model.param().set("R2", "250[mm]");
        this.model.param().set("H2", "2.5[cm]");
        this.model.param().set("R21", "0.24837");
        this.model.param().set("Z2", "-0.0125 + 0.04");
        this.model.param().set("X1", "0");
        this.model.param().set("X2", "0");
        this.model.param().set("Y1", "0");
        this.model.param().set("Y2", "0");

        this.model.geom("geom1").create("cyl1", "Cylinder");
        this.model.geom("geom1").feature("cyl1").set("r", "R");
        this.model.geom("geom1").feature("cyl1").set("h", "H");
        this.model.geom("geom1").feature("cyl1").set("pos", new String[]{"X1", "Y1", "Z1"});
        this.model.geom("geom1").run("cyl1");
        this.model.geom("geom1").create("cyl2", "Cylinder");
        this.model.geom("geom1").feature("cyl2").set("r", "R1");
        this.model.geom("geom1").feature("cyl2").set("h", "H1");
        this.model.geom("geom1").feature("cyl2").set("pos", new String[]{"X1", "Y1", "Z1"});
        this.model.geom("geom1").feature("cyl2").set("h", "H");
        this.model.geom("geom1").feature("cyl2").set("axistype", "spherical");
        this.model.geom("geom1").feature("cyl2").set("selresult", "on");
        this.model.geom("geom1").feature("cyl2").set("selresultshow", "all");
        this.model.geom("geom1").feature("cyl1").set("selresult", "on");
        this.model.geom("geom1").feature("cyl1").set("selresultshow", "all");
        this.model.geom("geom1").run("cyl2");
        this.model.geom("geom1").create("dif1", "Difference");
        this.model.geom("geom1").feature("dif1").selection("input").named("cyl1");
        this.model.geom("geom1").feature("dif1").selection("input2").named("cyl2");
        this.model.geom("geom1").feature("dif1").set("selresult", "on");
        this.model.geom("geom1").feature("dif1").set("selresultshow", "all");
        this.model.geom("geom1").run("dif1");
        this.model.geom("geom1").create("cyl3", "Cylinder");
        this.model.geom("geom1").feature("cyl3").set("r", "R2");
        this.model.geom("geom1").feature("cyl3").set("h", "H2");
        this.model.geom("geom1").feature("cyl3").set("pos", new String[]{"X2", "Y2", "Z2"});
        this.model.geom("geom1").run("cyl3");
        this.model.geom("geom1").create("cyl4", "Cylinder");
        this.model.geom("geom1").feature("cyl4").set("r", "R21");
        this.model.geom("geom1").feature("cyl4").set("h", "H2");
        this.model.geom("geom1").feature("cyl4").set("selresult", "on");
        this.model.geom("geom1").feature("cyl4").set("selresultshow", "all");
        this.model.geom("geom1").feature("cyl3").set("selresult", "on");
        this.model.geom("geom1").feature("cyl3").set("selresultshow", "all");
        this.model.geom("geom1").run("cyl4");
        this.model.geom("geom1").create("dif2", "Difference");
        this.model.geom("geom1").feature("dif2").selection("input").named("cyl3");
        this.model.geom("geom1").feature("dif2").selection("input2").named("cyl4");
        this.model.geom("geom1").feature("dif2").set("selresult", "on");
        this.model.geom("geom1").feature("dif2").set("selresultshow", "all");
        this.model.geom("geom1").feature("cyl4").set("pos", new String[]{"X2", "Y2", "Z2"});
        this.model.geom("geom1").run("dif2");
        this.model.geom("geom1").create("cyl5", "Cylinder");
        this.model.geom("geom1").feature("cyl5").set("r", "R3");
        this.model.geom("geom1").feature("cyl5").set("h", "H3");
        this.model.geom("geom1").feature("cyl5").set("pos", new String[]{"0", "0", "Zplane3"});
        this.model.geom("geom1").feature("cyl5").set("selresult", "on");
        this.model.geom("geom1").feature("cyl5").set("selresultshow", "all");
        this.model.geom("geom1").runPre("fin");

        this.model.view("view1").set("drawhidestatus", "hide");

        this.model.geom("geom1").run("fin");

        this.model.material().create("mat1", "Common", "comp1");
        this.model.material("mat1").label("Air [gas]");
        this.model.material("mat1").info().create("Composition");
        this.model.material("mat1").info("Composition").body("78.09 N2, 20.95 O2, 0.93 Ar, 0.039 CO2, trace others (vol%)");
        this.model.material("mat1").set("family", "custom");
        this.model.material("mat1").set("lighting", "simple");
        this.model.material("mat1").set("ambient", "custom");
        this.model.material("mat1").set("customambient", new double[]{0.9019607843137255, 0.9019607843137255, 1});
        this.model.material("mat1").set("diffuse", "custom");
        this.model.material("mat1").set("customdiffuse", new double[]{0.9019607843137255, 0.9019607843137255, 1});
        this.model.material("mat1").set("noisescale", 0.08);
        this.model.material("mat1").set("noise", "on");
        this.model.material("mat1").set("noisefreq", 3);
        this.model.material("mat1").set("noise", "on");
        this.model.material("mat1").set("alpha", 1);
        this.model.material("mat1").set("shininess", 0);
        this.model.material("mat1").propertyGroup("def").set("thermalconductivity", "k(T[1/K])[W/(m*K)]");
        this.model.material("mat1").propertyGroup("def").set("heatcapacity", "C(T[1/K])[J/(kg*K)]");
        this.model.material("mat1").propertyGroup("def").set("density", "rho_gas_2(T[1/K])[kg/m^3]");
        this.model.material("mat1").propertyGroup("def").set("TD", "TD(T[1/K])[m^2/s]");
        this.model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T[1/K])[Pa*s]");
        this.model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
        this.model.material("mat1").propertyGroup("def").func("k").set("funcname", "k");
        this.model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
        this.model.material("mat1").propertyGroup("def").func("k").set("extrap", "constant");
        this.model.material("mat1").propertyGroup("def").func("k")
                .set("pieces", new String[][]{{"70.0", "1000.0", "-8.404165E-4+1.107418E-4*T^1-8.635537E-8*T^2+6.31411E-11*T^3-1.88168E-14*T^4"}});
        this.model.material("mat1").propertyGroup("def").func().create("C", "Piecewise");
        this.model.material("mat1").propertyGroup("def").func("C").set("funcname", "C");
        this.model.material("mat1").propertyGroup("def").func("C").set("arg", "T");
        this.model.material("mat1").propertyGroup("def").func("C").set("extrap", "constant");
        this.model.material("mat1").propertyGroup("def").func("C")
                .set("pieces", new String[][]{{"100.0", "375.0", "1010.97+0.0439479*T^1-2.922398E-4*T^2+6.503467E-7*T^3"}, {"375.0", "1300.0", "1093.29-0.6355521*T^1+0.001633992*T^2-1.412935E-6*T^3+5.59492E-10*T^4-8.663072E-14*T^5"}, {"1300.0", "3000.0", "701.0807+0.8493867*T^1-5.846487E-4*T^2+2.302436E-7*T^3-4.846758E-11*T^4+4.23502E-15*T^5"}});
        this.model.material("mat1").propertyGroup("def").func().create("rho_gas_2", "Piecewise");
        this.model.material("mat1").propertyGroup("def").func("rho_gas_2").set("funcname", "rho_gas_2");
        this.model.material("mat1").propertyGroup("def").func("rho_gas_2").set("arg", "T");
        this.model.material("mat1").propertyGroup("def").func("rho_gas_2").set("extrap", "constant");
        this.model.material("mat1").propertyGroup("def").func("rho_gas_2")
                .set("pieces", new String[][]{{"80.0", "3000.0", "352.716*T^-1"}});
        this.model.material("mat1").propertyGroup("def").func().create("TD", "Piecewise");
        this.model.material("mat1").propertyGroup("def").func("TD").set("funcname", "TD");
        this.model.material("mat1").propertyGroup("def").func("TD").set("arg", "T");
        this.model.material("mat1").propertyGroup("def").func("TD").set("extrap", "constant");
        this.model.material("mat1").propertyGroup("def").func("TD")
                .set("pieces", new String[][]{{"300.0", "753.0", "1.713214E-4-1.204913E-6*T^1+2.839046E-9*T^2-1.532799E-12*T^3"}, {"753.0", "873.0", "0.00416418-1.191227E-5*T^1+8.863636E-9*T^2"}});
        this.model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
        this.model.material("mat1").propertyGroup("def").func("eta").set("funcname", "eta");
        this.model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
        this.model.material("mat1").propertyGroup("def").func("eta").set("extrap", "constant");
        this.model.material("mat1").propertyGroup("def").func("eta")
                .set("pieces", new String[][]{{"120.0", "600.0", "-1.132275E-7+7.94333E-8*T^1-7.197989E-11*T^2+5.158693E-14*T^3-1.592472E-17*T^4"}, {"600.0", "2150.0", "3.892629E-6+5.75387E-8*T^1-2.675811E-11*T^2+9.709691E-15*T^3-1.355541E-18*T^4"}});
        this.model.material("mat1").propertyGroup("def").addInput("temperature");
        this.model.material("mat1").set("family", "custom");
        this.model.material("mat1").set("lighting", "simple");
        this.model.material("mat1").set("ambient", "custom");
        this.model.material("mat1").set("customambient", new double[]{0.9019607843137255, 0.9019607843137255, 1});
        this.model.material("mat1").set("diffuse", "custom");
        this.model.material("mat1").set("customdiffuse", new double[]{0.9019607843137255, 0.9019607843137255, 1});
        this.model.material("mat1").set("noisescale", 0.08);
        this.model.material("mat1").set("noise", "on");
        this.model.material("mat1").set("noisefreq", 3);
        this.model.material("mat1").set("noise", "on");
        this.model.material("mat1").set("alpha", 1);
        this.model.material("mat1").set("shininess", 0);

        this.model.view("view1").set("transparency", "on");
        this.model.view("view1").set("renderwireframe", true);
        this.model.view("view1").set("transparency", "off");
        this.model.view("view1").set("renderwireframe", false);
        this.model.view("view1").set("transparency", "on");
        this.model.view("view1").set("geomhidestatus", "hide");

        this.model.material().create("mat2", "Common", "comp1");
        this.model.material("mat2").label("Copper");
        this.model.material("mat2").set("family", "copper");
        this.model.material("mat2").propertyGroup("def").set("relpermeability", "1");
        this.model.material("mat2").propertyGroup("def").set("electricconductivity", "5.998e7[S/m]");
        this.model.material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
        this.model.material("mat2").propertyGroup("def").set("relpermittivity", "1");
        this.model.material("mat2").propertyGroup("def").set("emissivity", "0.5");
        this.model.material("mat2").propertyGroup("def").set("density", "8700[kg/m^3]");
        this.model.material("mat2").propertyGroup("def").set("thermalconductivity", "400[W/(m*K)]");
        this.model.material("mat2").propertyGroup().create("linzRes", "Linearized resistivity");
        this.model.material("mat2").propertyGroup("linzRes").set("alpha", "3.9e-3[1/K]");
        this.model.material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
        this.model.material("mat2").propertyGroup("linzRes").set("Tref", "273.15[K]");
        this.model.material("mat2").set("family", "copper");
        this.model.material("mat2").selection().named("geom1_dif1_dom");
        this.model.material("mat2").selection().named("geom1_dif2_dom");
        this.model.material("mat2").selection().all();
        this.model.material("mat2").selection().set(new int[]{2, 3});
        this.model.material("mat1").propertyGroup("def").set("relpermeability", new String[]{"0"});
        this.model.material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
        this.model.material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
        this.model.material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"1"});

        this.model.physics("mf").create("coil1", "CoilBoundary", 2);
        this.model.physics("mf").feature().remove("coil1");

        this.model.param().set("N1", "11");
        this.model.param().set("N2", "11");

        this.model.physics("mf").create("coil1", "CoilBoundary", 2);
        this.model.physics("mf").feature().remove("coil1");
        this.model.physics("mf").create("coil1", "Coil", 3);
        this.model.physics("mf").feature("coil1").label("Multi-turn Coil 1");

        this.model.view("view1").set("geomhidestatus", "hide");
        this.model.view("view1").hideEntities().create("hide1");
        this.model.view("view1").hideEntities("hide1").geom(3);
        this.model.view("view1").hideEntities("hide1").add(new int[]{1});
        this.model.view("view1").set("geomhidestatus", "hide");

        this.model.physics("mf").feature("coil1").selection().set(new int[]{2});
        this.model.physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().all();
        this.model.physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(new int[]{});
        this.model.physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().named("geom1_dif1_bnd");
        this.model.physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection()
                .set(new int[]{5, 7, 8, 13, 14, 18, 20, 22, 24});
        this.model.physics("mf").create("coil2", "CoilBoundary", 2);
        this.model.physics("mf").feature("coil1").setIndex("materialType", "from_mat", 0);
        this.model.physics("mf").feature("coil1").set("ConductorModel", "Multi");
        this.model.physics("mf").feature("coil1").set("CoilType", "Circular");
        this.model.physics("mf").feature("coil1").set("N", "N1");
        this.model.physics("mf").feature("coil1").set("AreaFrom", "AWG");
        this.model.physics("mf").feature("coil1").set("AWGArea", "14");
        this.model.physics("mf").feature("coil1").feature("cre1").selection().set(new int[]{9, 10, 31, 49});
        this.model.physics("mf").feature("coil1").feature("cre1").set("AltCircular", true);
        this.model.physics("mf").feature().remove("coil2");
        this.model.physics("mf").create("coil2", "CoilBoundary", 2);
        this.model.physics("mf").feature().remove("coil2");
        this.model.physics("mf").create("coil2", "Coil", 3);
        this.model.physics("mf").feature("coil2").setIndex("materialType", "solid", 0);
        this.model.physics("mf").feature("coil2").label("Multi-turn Coil 2");
        this.model.physics("mf").feature("coil2").selection().set(new int[]{3});
        this.model.physics("mf").feature("coil2").setIndex("materialType", "from_mat", 0);

        this.model.sol().create("sol1");

        this.model.physics("mf").feature("coil2").set("ConductorModel", "Multi");
        this.model.physics("mf").feature("coil2").set("CoilType", "Circular");
        this.model.physics("mf").feature("coil2").set("N", "N2");
        this.model.physics("mf").feature("coil2").set("AreaFrom", "AWG");
        this.model.physics("mf").feature("coil2").set("AWGArea", "14");
        this.model.physics("mf").feature("coil2").feature("cre1").selection().set(new int[]{14, 25, 34, 46});
        this.model.physics("mf").feature("coil2").set("ICoil", "0[A]");
        this.model.physics("mf").feature("coil2").feature("cre1").label("Coil Geometry 2");
        this.model.physics("mf").feature("coil2").feature("cre1").set("AltCircular", true);

        this.model.sol().create("sol2");
        this.model.sol("sol2").study("std1");

        this.model.study("std1").feature("stat").set("notlistsolnum", 1);
        this.model.study("std1").feature("stat").set("notsolnum", "1");
        this.model.study("std1").feature("stat").set("listsolnum", 1);
        this.model.study("std1").feature("stat").set("solnum", "1");

        this.model.sol("sol2").create("st1", "StudyStep");
        this.model.sol("sol2").feature("st1").set("study", "std1");
        this.model.sol("sol2").feature("st1").set("studystep", "stat");
        this.model.sol("sol2").create("v1", "Variables");
        this.model.sol("sol2").feature("v1").set("control", "stat");
        this.model.sol("sol2").create("s1", "Stationary");
        this.model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
        this.model.sol("sol2").feature("s1").create("i1", "Iterative");
        this.model.sol("sol2").feature("s1").feature("i1").set("linsolver", "fgmres");
        this.model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("ams1", "AMS");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("ams1").set("prefun", "ams");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("ams1")
                .set("sorvecdof", new String[]{"comp1_A"});
        this.model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "i1");
        this.model.sol("sol2").feature("s1").feature().remove("fcDef");
        this.model.sol("sol2").attach("std1");

        this.model.result().numerical().create("gev1", "EvalGlobal");
        this.model.result().numerical("gev1").set("data", "dset2");
        this.model.result().numerical("gev1").set("expr", new String[]{"mf.L_2_1"});
        this.model.result().numerical("gev1").set("descr", new String[]{"Mutual inductance between coils 2 and 1"});

        this.model.sol("sol2").study("std1");

        this.model.study("std1").feature("stat").set("notlistsolnum", 1);
        this.model.study("std1").feature("stat").set("notsolnum", "1");
        this.model.study("std1").feature("stat").set("listsolnum", 1);
        this.model.study("std1").feature("stat").set("solnum", "1");

        this.model.sol("sol2").feature().remove("s1");
        this.model.sol("sol2").feature().remove("v1");
        this.model.sol("sol2").feature().remove("st1");
        this.model.sol("sol2").create("st1", "StudyStep");
        this.model.sol("sol2").feature("st1").set("study", "std1");
        this.model.sol("sol2").feature("st1").set("studystep", "stat");
        this.model.sol("sol2").create("v1", "Variables");
        this.model.sol("sol2").feature("v1").set("control", "stat");
        this.model.sol("sol2").create("s1", "Stationary");
        this.model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
        this.model.sol("sol2").feature("s1").create("i1", "Iterative");
        this.model.sol("sol2").feature("s1").feature("i1").set("linsolver", "fgmres");
        this.model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("ams1", "AMS");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("ams1").set("prefun", "ams");
        this.model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("ams1")
                .set("sorvecdof", new String[]{"comp1_A"});
        this.model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "i1");
        this.model.sol("sol2").feature("s1").feature().remove("fcDef");
        this.model.sol("sol2").attach("std1");

        this.model.mesh("mesh1").run();

        this.model.sol("sol2").feature("s1").feature("i1").set("maxrefinesteps", "100");

        this.model.geom("geom1").feature("cyl1").set("axistype", "x");
        this.model.geom("geom1").feature("cyl2").set("axistype", "z");
        this.model.geom("geom1").feature("dif1").set("repairtoltype", "relative");
        this.model.geom("geom1").feature("dif1").set("repairtol", "1.0E-6");
        this.model.geom("geom1").feature("dif2").set("repairtoltype", "relative");
        this.model.geom("geom1").feature("dif2").set("repairtol", "1.0E-6");
        this.model.geom("geom1").feature("fin").set("repairtoltype", "relative");
        this.model.geom("geom1").feature("fin").set("repairtol", "1E-6");
        this.model.geom("geom1").run("fin");
        this.model.geom("geom1").feature("cyl1").set("axistype", "z");
        this.model.geom("geom1").runPre("fin");
        this.model.geom("geom1").run("fin");

        this.model.material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
        this.model.material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});

        this.model.sol("sol2").runAll();

        this.model.result().table().create("tbl1", "Table");
        this.model.result().table("tbl1").comments("Global Evaluation 1 (mf.L_2_1)");
        this.model.result().numerical("gev1").set("table", "tbl1");
        this.model.result().numerical("gev1").setResult();
    }

    public Model GetModel(){
        return this.model;
    }

    public double[][] Run(){
        double[][] result = (double[][])null;
        this.model.sol("sol1").runAll();
        result = this.GetMutualInductance();
        return result;
    }

    public double[][] GetMutualInductance(){
        this.model.result().numerical("gev1").run();
        return this.model.result().numerical("gev1").getReal();
    }

    public void SetCoilParams(String[] coil1_param, String[] coil2_param){
        this.model.param().set("R", coil1_param[0], "1-st coil radius");
        this.model.param().set("R2", coil2_param[0], "2-nd coil radius");
        this.model.param().set("H", coil1_param[1], "1-st coil height");
        this.model.param().set("H2", coil2_param[1], "2-nd coil height");
        this.model.param().set("N1", coil1_param[2], "Number of counts 1-st coil");
        this.model.param().set("N2", coil2_param[2], "Number of counts 2-nd coil");
        this.model.param().set("X1", coil1_param[3], "1-st coil Ox position");
        this.model.param().set("X2", coil2_param[3], "2-nd coil Ox position");
        this.model.param().set("Y1", coil1_param[4], "1-st coil Oy position");
        this.model.param().set("Y2", coil2_param[4], "2-nd coil Oy position");
        this.model.param().set("Z1", coil1_param[5], "1-st coil Oz position");
        this.model.param().set("Z2", coil2_param[5], "2-nd coil Oz position");
    }

}
        /*public void SetCoilParams(String[] coil1_param, String[] coil2_param){
            this.model.param().set("R1", coil1_param[0], "1-st coil radius");
            this.model.param().set("R2", coil2_param[0], "2-nd coil radius");
            this.model.param().set("H1", coil1_param[1], "1-st coil height");
            this.model.param().set("H2", coil2_param[1], "2-nd coil height");
            this.model.param().set("N1", coil1_param[2], "Number of counts 1-st coil");
            this.model.param().set("N2", coil2_param[2], "Number of counts 2-nd coil");
            this.model.param().set("X1", coil1_param[3], "1-st coil Ox position");
            this.model.param().set("X2", coil2_param[3], "2-nd coil Ox position");
            this.model.param().set("Y1", coil1_param[4], "1-st coil Oy position");
            this.model.param().set("Y2", coil2_param[4], "2-nd coil Oy position");
            this.model.param().set("Z1", coil1_param[5], "1-st coil Oz position");
            this.model.param().set("Z2", coil2_param[5], "2-nd coil Oz position");
        }

        public double[][] Run(){
            double[][] result = (double[][])null;
            this.model.sol("sol1").runAll();
            result = this.GetMutualInductance();
            return result;
        }

        public double[][] GetMutualInductance(){
            this.model.result().numerical("gev1").run();
            return this.model.result().numerical("gev1").getReal();
        }*/