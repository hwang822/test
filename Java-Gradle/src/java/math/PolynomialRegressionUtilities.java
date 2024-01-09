/** */
package math;

import com.jme3.math.Vector2f;
import core.GlobalConstants;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Utility class for a collection of mathematical functions on polynoms.
 *
 * @ingroup ViewData/math
 * @author Daniela
 * @date 22.08.2016
 * @version $Id$
 * @copyright 2016 CA Digital GmbH. All rights reserved.
 */
public class PolynomialRegressionUtilities {

    /**
     * polynomial regression: computes coefficients a, b of line y = ax + b
     *
     * @param list - 2D positions (size >= 2)
     * @param weights - weighting factors for the points of list
     * @return polynom
     */
    public static PolynomialFunction computeRegressionLine(
            List<Vector2f> coords, List<Integer> weights) {
        double x_average = 0.0;
        double y_average = 0.0;

        for (int i = 0; i < coords.size(); ++i) {
            Vector2f coord = coords.get(i);

            x_average += coord.x;
            y_average += coord.y;
        }
        x_average /= coords.size();
        y_average /= coords.size();

        double sum_diff_squared_x = 0;
        double sum_diff_x_diff_y = 0;
        for (int i = 0; i < coords.size(); ++i) {
            Vector2f coord = coords.get(i);
            sum_diff_squared_x += (coord.x - x_average) * (coord.x - x_average);
            sum_diff_x_diff_y += (coord.x - x_average) * (coord.y - y_average);
        }

        if (MathUtilities.isNearlyEqual(sum_diff_squared_x, 0.0)) return null;

        double a = sum_diff_x_diff_y / sum_diff_squared_x;
        double b = y_average - a * x_average;
        return new PolynomialFunction(new double[] {b, a});
    }

    /**
     * computes the intersection point of the given polynom with the line defined by direction
     * vector and origin (x, y)
     *
     * @param direction
     * @param polynom
     * @param x
     * @param y
     * @return the the components of intersection point and the value of the derivative of the
     *     polynom in this position
     */
    public static double[] computeNearestIntersectionBetweenPolynomAndLine(
            Vector2f direction, PolynomialFunction polynom, double x, double y) {
        LaguerreSolver solver1 = new LaguerreSolver();
        double[] coefficients;
        if (polynom.getCoefficients().length > 1) coefficients = polynom.getCoefficients().clone();
        else {
            coefficients = new double[2];
            coefficients[0] = polynom.getCoefficients()[0];
        }

        BisectionSolver solver = new BisectionSolver();

        // subtract line equation f(x) = m*x + b from polynomial function for intersection
        // computation
        double b = y - x * direction.y / direction.x;
        float m = direction.y / direction.x;

        //		GlobalConstants.LOGGER.info( "line equation f(x) = " + m + "x + " + b );

        coefficients[0] -= b; // = b
        coefficients[1] -= m; // = m

        double newX = x;
        double minX = 1000;
        try {
            Complex[] roots = solver1.solveAllComplex(coefficients, x, 100);
            for (int i = 0; i < roots.length; ++i) {
                double current = Math.abs(roots[i].getReal() - x);
                if (current < minX && roots[i].getImaginary() == 0.0)
                //						&& direction.dot( new Vector2f( (float) newX, (float) polynom.value( newX )
                // ).subtract( new Vector2f( (float) x, (float) y ) ) ) > 0 )
                {
                    minX = current;
                    newX = roots[i].getReal();
                }
            }
        } catch (Exception e) {
            if (e instanceof TooManyEvaluationsException
                    || e instanceof NoDataException
                    || e instanceof NullArgumentException) {
                // GlobalConstants.LOGGER.warn("no intersection point found with LaguerreSolver");
                //				GlobalConstants.LOGGER.fatal(ExceptionUtils.getStackTrace(e));

                double min = x - 1;
                double max = x + 1;
                PolynomialFunction intersectionPolynom = new PolynomialFunction(coefficients);

                try {
                    newX = solver.solve(100, intersectionPolynom, min, max);
                } catch (Exception e2) {
                    GlobalConstants.LOGGER.warn("no intersection point found with BisectionSolver");
                    newX = x;
                }
            } else {
                GlobalConstants.LOGGER.fatal(ExceptionUtils.getStackTrace(e));
            }
        }
        return new double[] {newX, polynom.value(newX), polynom.derivative().value(newX)};
    }

    /**
     * @param polynom
     * @param vector2f
     * @param vector2f2
     * @return
     */
    public static Vector2D computeInflectionPointWithinInterval(
            PolynomialFunction polynom, double minX, double maxX) {
        if (minX > maxX) {
            double tmp = minX;
            minX = maxX;
            maxX = tmp;
        }
        double[] coefficients =
                polynom.polynomialDerivative().polynomialDerivative().getCoefficients();

        BisectionSolver solver2 = new BisectionSolver(9E-15);
        PolynomialFunction secondDerivative = new PolynomialFunction(coefficients);

        try {
            double newX = solver2.solve(1000, secondDerivative, minX, maxX);
            double value = secondDerivative.polynomialDerivative().value(newX);
            if (MathUtilities.isNearlyEqual(value, 0.0)) {
                //				GlobalConstants.LOGGER.warn("extreme value is no inflection point");
                return null;
            } else {
                return new Vector2D(newX, polynom.value(newX));
            }
        } catch (Exception e) {
            GlobalConstants.LOGGER.warn("no inflection point found with BisectionSolver");
        }

        return null;
    }
}
