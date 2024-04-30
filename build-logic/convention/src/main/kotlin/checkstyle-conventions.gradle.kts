import org.geogebra.build.Resources

plugins {
    checkstyle
}

checkstyle {
    toolVersion = "10.3.3"
    config = resources.text.fromString(Resources.getString("/checkstyle/checkstyle.xml"))
}
