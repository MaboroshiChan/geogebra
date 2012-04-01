package geogebra.common.kernel.commands;

import geogebra.common.kernel.Kernel;
import geogebra.common.kernel.arithmetic.Command;
import geogebra.common.kernel.arithmetic.NumberValue;
import geogebra.common.kernel.geos.GeoElement;
import geogebra.common.kernel.geos.GeoPoint2;
import geogebra.common.main.MyError;
/**
 * 
 * Trilinear[point,point,point,coord,coord,coord]
 *
 */
public class CmdTrilinear extends CommandProcessor {

	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdTrilinear(Kernel kernel) {
		super(kernel);
	}

	@Override
	final public GeoElement[] process(Command c) throws MyError {
		int n = c.getArgumentNumber();
		boolean[] ok = new boolean[n];
		GeoElement[] arg;

		switch (n) {
		case 6:
			arg = resArgs(c);
			if ((ok[0] = arg[0].isGeoPoint()) &&
					(ok[1] = arg[1].isGeoPoint()) &&
					(ok[2] = arg[2].isGeoPoint()) &&
					(ok[3] = arg[3].isNumberValue()) &&
					(ok[4] = arg[5].isNumberValue()) &&
					(ok[5] = arg[5].isNumberValue())) {
				GeoElement[] ret = { kernelA.Trilinear(c.getLabel(),
						(GeoPoint2)arg[0], (GeoPoint2)arg[1], (GeoPoint2)arg[2],
						(NumberValue) arg[3], (NumberValue) arg[4], (NumberValue) arg[5])} ;
				return ret;
				
			} 
			throw argErr(app, c.getName(), getBadArg(ok,arg));
		default:
			throw argNumErr(app, c.getName(), n);
		}
	}
}
