package geogebra.kernel.commands;

import geogebra.kernel.Kernel;
import geogebra.kernel.arithmetic.Command;
import geogebra.kernel.geos.GeoConic;
import geogebra.kernel.geos.GeoElement;
import geogebra.kernel.geos.GeoFunction;
import geogebra.kernel.implicit.GeoImplicitPoly;
import geogebra.main.MyError;

/**
 * Asymptote[ <GeoConic> ]
 */
class CmdAsymptote extends CommandProcessor {

	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdAsymptote(Kernel kernel) {
		super(kernel);
	}

	final public GeoElement[] process(Command c) throws MyError {
		int n = c.getArgumentNumber();
		GeoElement[] arg;

		switch (n) {
		case 1:
			arg = resArgs(c);

			// asymptotes to conic
			if (arg[0].isGeoConic())
				return kernel.Asymptote(c.getLabels(), (GeoConic) arg[0]);
			else if (arg[0].isGeoFunction())
			{
				GeoElement[] ret = { kernel.AsymptoteFunction(c.getLabel(),
						(GeoFunction) arg[0]) };
				return ret;
			}
			else if (arg[0].isGeoImplicitPoly()) {
				GeoElement[] ret =  {kernel.AsymptoteImplicitpoly(c.getLabel(),
						(GeoImplicitPoly) arg[0])} ;
				return ret;
			}
			throw argErr(app, "Asymptote", arg[0]);

		default:
			throw argNumErr(app, "Asymptote", n);
		}
	}
}
