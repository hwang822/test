package math;

import com.jme3.bounding.BoundingBox;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResults;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Matrix4f;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Spline;
import com.jme3.math.Triangle;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import core.GlobalConstants;
import core.NoNegativeZeroNumberFormatter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealVector;

/**
 * Collection of useful mathematical methods.
 *
 * @ingroup math
 * @author Daniela Dudai
 * @date 15.10.2014
 * @version $Id$
 * @copyright 2014 CA Digital GmbH. All rights reserved.
 */
public class MathUtilities {
    public static NoNegativeZeroNumberFormatter numberFormatter =
            new NoNegativeZeroNumberFormatter(new DecimalFormat("0.0"));
    public static NoNegativeZeroNumberFormatter numberFormatter2Digits =
            new NoNegativeZeroNumberFormatter(new DecimalFormat("0.00"));

    /**
     * Clamp given value to range [min, max]
     *
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static float clamp(float value, float min, float max) {
        return (value < min) ? min : (value > max) ? max : value;
    }

    /**
     * Round value of d to the next .0 or .5;
     *
     * @param d
     * @return
     */
    public static double roundToHalf(double d) {
        return Math.round(d * 2) * 0.5f;
    }

    /**
     * rounds a decimal of a double to the nearest decimal; i.e. 0.24 => 0.2 , 0.25 => 0.3
     *
     * @param value to be rounded
     * @return
     * @return a double, with the decimal rounded to the nearest value
     */
    public static double roundDecimal(double value) {
        return (Math.round(value * 10.0)) * 0.1;
    }

    /**
     * rounds the decimals of a double to the nearest two decimals; i.e. 0.245 => 0.24 , 0.245 =>
     * 0.25
     *
     * @param value to be rounded
     * @return
     * @return a double, with the decimals rounded to the nearest value
     */
    public static double roundTwoDecimal(double value) {
        return (Math.round(value * 100.0)) * 0.01;
    }

    /**
     * returns a double equal to the provided string
     *
     * @param value string
     * @return value double or 0.0 if string was not correctly formatted
     */
    public static double stringToDouble(String value) {
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException e) {
            GlobalConstants.LOGGER.info("Number format incorrect. Skipping stringToDouble()");
            return 0.0;
        }
    }

    /**
     * returns a string equal to the provided value formatted with the provided DecimalFormat
     *
     * @param formatter to use to format value
     * @param value to format
     * @return string of provided value
     */
    public static String doubleToString(double value) {
        try {
            return MathUtilities.numberFormatter.valueToString(value);
        } catch (ParseException e) {
            GlobalConstants.LOGGER.info("Number format incorrect. Skipping doubleToString()");
            return "";
        }
    }

    public static String doubleToString2(double value) {
        try {
            return MathUtilities.numberFormatter2Digits.valueToString(value);
        } catch (ParseException e) {
            GlobalConstants.LOGGER.info("Number format incorrect. Skipping doubleToString2()");
            return "";
        }
    }

    public static boolean isNearlyEqual(double value1, double value2, double maxOffset) {
        if (value1 - maxOffset < value2 && value1 + maxOffset > value2) {
            return true;
        }
        return false;
    }

    public static boolean isNearlyEqual(double value1, double value2) {
        double maxOffset = 1E-10;
        return isNearlyEqual(value1, value2, maxOffset);
    }

    /**
     * computes the orthogonal projection of the point in the plane defined by planeNormal and
     * planeOrigin.
     *
     * @param point
     * @param planeNormal
     * @param planeOrigin
     * @return the projected point
     */
    public static Vector3f projectionOnPlane(
            Vector3f point, Vector3f planeOrigin, Vector3f planeNormal) {
        float s =
                (planeOrigin.dot(planeNormal) - point.dot(planeNormal))
                        / planeNormal.dot(planeNormal);

        return point.add(planeNormal.mult(s));
    }

    /**
     * computes the 2D postion of the 3D point in the coordinate system defined by origin and the
     * vectors xAxis and yAxis.
     *
     * @param pos3D
     * @param xAxis
     * @param yAxis
     * @param origin
     * @return the 2D position of pos3D
     */
    public static Vector2f compute2DPosOnPlane(
            Vector3f pos3D, Vector3f xAxis, Vector3f yAxis, Vector3f origin) {
        float x = pos3D.subtract(origin).dot(xAxis);
        float y = pos3D.subtract(origin).dot(yAxis);

        return new Vector2f(x, y);
    }

    /**
     * computes the 2D vector of the 3D vector in the coordinate system defined by the vectors xAxis
     * and yAxis.
     *
     * @param pos3D
     * @param xAxis
     * @param yAxis
     * @param origin
     * @return the 2D position of pos3D
     */
    public static Vector2f compute2DVecOnPlane(Vector3f vec3D, Vector3f xAxis, Vector3f yAxis) {
        float x = vec3D.dot(xAxis);
        float y = vec3D.dot(yAxis);

        return new Vector2f(x, y);
    }

    /**
     * computes the 3D position of the 2D point in the coordinate system defined by origin and the
     * vectors xAxis and yAxis.
     *
     * @param pos2D
     * @param xAxis
     * @param yAxis
     * @param origin
     * @return the 3D position of pos2D
     */
    public static Vector3f compute3DPosOnPlane(
            Vector2f pos2D, Vector3f xAxis, Vector3f yAxis, Vector3f origin) {
        Vector3f pos3D = compute3DVectorOnPlane(pos2D, xAxis, yAxis).add(origin);

        return pos3D;
    }

    /**
     * computes the 3D vector of the 2D vector which is a projection in the coordinate system
     * defined by the vectors xAxis and yAxis.
     *
     * @param pos2D
     * @param xAxis
     * @param yAxis
     * @return
     */
    public static Vector3f compute3DVectorOnPlane(Vector2f pos2D, Vector3f xAxis, Vector3f yAxis) {
        return xAxis.mult(pos2D.x).add(yAxis.mult(pos2D.y));
    }

    /**
     * creates a mesh between spline1 and spline2.
     *
     * @param spline1
     * @param spline2
     * @return the mesh spanned by spline1 and spline2
     */
    public static Mesh createMeshBetweenSplines(Spline spline1, Spline spline2, int samples) {
        List<Vector3f> knots1 = spline1.getControlPoints();
        int knotsSize = knots1.size();

        Vector3f[] vertices = new Vector3f[samples * (knotsSize - 1) * 2 + 2];
        Vector3f[] normals = new Vector3f[samples * (knotsSize - 1) * 2 + 2];
        Vector4f[] colors = new Vector4f[vertices.length];
        Vector3f a = spline1.getControlPoints().get(1).subtract(spline1.getControlPoints().get(0));
        Vector3f b = spline2.getControlPoints().get(0).subtract(spline1.getControlPoints().get(0));
        Vector3f normal = b.cross(a).normalize();
        Vector4f color = ColorRGBA.LightGray.toVector4f();
        int index = 0;

        // create triangle strip between splines
        for (int i = 0; i < knotsSize - 1; ++i) {
            for (int j = 0; j < samples; ++j) {
                float value = (float) j / (float) samples;
                vertices[index] = (spline1.interpolate(value, i, null));
                colors[index] = color;
                index++;

                vertices[index] = (spline2.interpolate(value, i, null));
                colors[index] = color;
                index++;

                // compute normal
                Vector3f nextPos = spline1.interpolate(value + 1.f / samples, i, null);
                a = nextPos.subtract(vertices[index - 2]);
                b = vertices[index - 1].subtract(vertices[index - 2]);
                Vector3f currentNormal = b.cross(a);
                normals[index - 2] = currentNormal.normalize();
                normals[index - 1] = currentNormal.normalize();
            }
        }
        vertices[index] = spline1.interpolate(1.f, knotsSize - 2, null);
        colors[index] = color;
        normals[index] = normal;
        vertices[index + 1] = spline2.interpolate(1.f, knotsSize - 2, null);
        colors[index + 1] = color;
        normals[index + 1] = normal;

        int n = 3 * (vertices.length - 2);
        int[] indices = new int[n];
        for (int i = 0; i < (n - 5) / 3; i += 2) {
            indices[i * 3] = i + 2;
            indices[i * 3 + 1] = i + 3;
            indices[i * 3 + 2] = i + 1;
            indices[i * 3 + 3] = i + 1;
            indices[i * 3 + 4] = i;
            indices[i * 3 + 5] = i + 2;
        }

        Mesh mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Triangles);
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(indices));
        mesh.setBuffer(Type.Color, 4, BufferUtils.createFloatBuffer(colors));
        mesh.updateCounts();
        mesh.setBound(new BoundingBox());
        mesh.updateBound();

        return mesh;
    }

    /**
     * transforms the mesh with the given transformation matrix.
     *
     * @param mesh
     * @param matrix
     */
    public static void transformMesh(Mesh mesh, Matrix4f matrix) {
        if (mesh.getBuffer(VertexBuffer.Type.Position) == null) {
            return;
        }
        VertexBuffer vertexBuffer = mesh.getBuffer(VertexBuffer.Type.Position).clone();

        VertexBuffer normalBuffer = null;
        if (mesh.getBuffer(VertexBuffer.Type.Normal) != null) {
            normalBuffer = mesh.getBuffer(VertexBuffer.Type.Normal).clone();
        }

        for (int i = 0; i < vertexBuffer.getNumElements(); ++i) {
            // rotate vertex
            float x = (float) vertexBuffer.getElementComponent(i, 0);
            float y = (float) vertexBuffer.getElementComponent(i, 1);
            float z = (float) vertexBuffer.getElementComponent(i, 2);
            Vector4f position = new Vector4f(x, y, z, 1);

            Vector4f newPosition = matrix.mult(position);

            vertexBuffer.setElementComponent(i, 0, newPosition.x);
            vertexBuffer.setElementComponent(i, 1, newPosition.y);
            vertexBuffer.setElementComponent(i, 2, newPosition.z);

            // rotate normal
            if (normalBuffer != null) {
                float nx = (float) normalBuffer.getElementComponent(i, 0);
                float ny = (float) normalBuffer.getElementComponent(i, 1);
                float nz = (float) normalBuffer.getElementComponent(i, 2);
                Vector4f normal = new Vector4f(nx, ny, nz, 0);

                Vector4f newNormal = matrix.mult(normal);

                normalBuffer.setElementComponent(i, 0, newNormal.x);
                normalBuffer.setElementComponent(i, 1, newNormal.y);
                normalBuffer.setElementComponent(i, 2, newNormal.z);
            }
        }

        mesh.clearBuffer(VertexBuffer.Type.Position);
        mesh.setBuffer(vertexBuffer);
        if (normalBuffer != null) {
            mesh.clearBuffer(VertexBuffer.Type.Normal);
            mesh.setBuffer(normalBuffer);
        }
    }

    /**
     * @param triangle
     * @param point
     * @return true if the point is within the triangle and false otherwise
     */
    public static boolean withinTriangle(Triangle triangle, Vector3f point) {
        if (projectionOnPlane(point, triangle.getCenter(), triangle.getNormal()).distance(point)
                > 0.01f) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (triangle.get(i).equals(point)) {
                return true;
            }
        }
        Vector3f dc = triangle.get3().subtract(triangle.get1());
        Vector3f dx = point.subtract(triangle.get1());
        Vector3f db = triangle.get2().subtract(triangle.get1());

        float dot_db_dc = db.dot(dc);
        float dot_dx_dc = dx.dot(dc);
        float dot_dx_db = dx.dot(db);
        float dot_db_db = db.dot(db);
        float dot_dc_dc = dc.dot(dc);

        float d = dot_db_db * dot_dc_dc - dot_db_dc * dot_db_dc;
        float r = (dot_dx_db * dot_dc_dc - dot_dx_dc * dot_db_dc) / d;
        float s = (dot_db_db * dot_dx_dc - dot_db_dc * dot_dx_db) / d;

        boolean within = r >= 0 && s >= 0 && r + s <= 1;
        return within;
    }

    /**
     * computes the regression plane through the given points.
     *
     * @param coords - list of points
     * @return plane
     */
    public static Plane computeRegressionPlane(List<Vector3f> coords) {
        Plane plane = new Plane();

        Vector3f averageCoord = new Vector3f(0, 0, 0);
        for (Vector3f coord : coords) {
            averageCoord.addLocal(coord);
        }
        averageCoord.divideLocal(coords.size());

        float sum_xi_x_2 = 0;
        float sum_yi_y_2 = 0;
        float sum_zi_z_2 = 0;
        float sum_xi_x_yi_y = 0;
        float sum_xi_x_zi_z = 0;
        float sum_yi_y_zi_z = 0;
        for (Vector3f coord : coords) {
            float xi = coord.x;
            float yi = coord.y;
            float zi = coord.z;
            sum_xi_x_2 += (xi - averageCoord.x) * (xi - averageCoord.x);
            sum_yi_y_2 += (yi - averageCoord.y) * (yi - averageCoord.y);
            sum_zi_z_2 += (zi - averageCoord.z) * (zi - averageCoord.z);

            sum_xi_x_yi_y += (xi - averageCoord.x) * (yi - averageCoord.y);
            sum_xi_x_zi_z += (xi - averageCoord.x) * (zi - averageCoord.z);
            sum_yi_y_zi_z += (yi - averageCoord.y) * (zi - averageCoord.z);
        }

        double[][] rawData = {
            {sum_xi_x_2, sum_xi_x_yi_y, sum_xi_x_zi_z},
            {sum_xi_x_yi_y, sum_yi_y_2, sum_yi_y_zi_z},
            {sum_xi_x_zi_z, sum_yi_y_zi_z, sum_zi_z_2}
        };

        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(rawData);

        EigenDecomposition eigenDecomposition = null;
        try {
            eigenDecomposition = new EigenDecomposition(blockRealMatrix);
        } catch (MaxCountExceededException e) {
            GlobalConstants.LOGGER.fatal(
                    "Calculation of regression plane not possible\n"
                            + ExceptionUtils.getStackTrace(e));
            plane.setOriginNormal(averageCoord, Vector3f.UNIT_Z.clone());
            return plane;
        }

        double[] eigenValues = eigenDecomposition.getRealEigenvalues();

        int minEigenIndex = 0;
        double minEigen = Double.MAX_VALUE;
        for (int i = 0; i < eigenValues.length; ++i) {
            if (eigenValues[i] < minEigen) {
                minEigen = eigenValues[i];
                minEigenIndex = i;
            }
        }

        RealVector eigenVector = eigenDecomposition.getEigenvector(minEigenIndex);

        plane.setOriginNormal(
                averageCoord,
                new Vector3f(
                        (float) eigenVector.getEntry(0),
                        (float) eigenVector.getEntry(1),
                        (float) eigenVector.getEntry(2)));
        return plane;
    }

    /**
     * create new point from innerPoint with the property that a sphere with given radius through
     * the innerpoint doesn't collide with the spatial
     *
     * <p>|__ ___|**** |__****** <---- sphere (innerPoint is a point on the surface of the sphere)
     * ___|**** | <---- spatial
     *
     * @param direction
     * @param spatial
     * @param radius
     * @param innerPoint
     * @param offset
     */
    public static void computeOuterPointFromInnerPoint(
            Vector3f direction,
            Spatial spatial,
            float radius,
            Vector3f innerPoint,
            float offset,
            Vector3f planeNormal) {
        Vector3f radiusOffset = direction.mult(-radius - offset);
        BoundingSphere testSphere = new BoundingSphere(radius, innerPoint.add(radiusOffset));
        CollisionResults results = new CollisionResults();
        Plane plane = new Plane();
        plane.setOriginNormal(innerPoint, planeNormal);
        while (spatial.collideWith(testSphere, results) > 0
                && plane.getClosestPoint(results.getClosestCollision().getContactPoint())
                                .distance(results.getClosestCollision().getContactPoint())
                        < 0.01f) {
            innerPoint.addLocal(direction.mult(-0.01f));
            testSphere.setCenter(innerPoint.add(radiusOffset));
        }
    }

    /**
     * directed smoothing of 3d points
     *
     * @param points
     * @param orientation
     */
    public static void smoothPeaksWithOrientation(List<Vector3f> points, Vector3f orientation) {
        for (int i = 0; i < points.size(); i++) {
            Vector3f pos0 = points.get(i == 0 ? points.size() - 1 : i - 1);
            Vector3f pos1 = points.get(i);
            Vector3f pos2 = points.get(i == points.size() - 1 ? 0 : i + 1);

            Vector3f center = pos0.add(pos2).divide(2.f);
            float dot = center.subtract(pos1).dot(orientation);
            if (dot < 0.f) // if new position is outside of the bracket -> no translation in
            // direction to bracket possible
            {
                points.set(i, center);
            }
        }
    }

    /**
     * @param absoluteOffsets
     * @return the absolute differences between successive entries
     */
    public static ArrayList<Float> getDifferences(ArrayList<Float> absoluteOffsets) {
        ArrayList<Float> differences = new ArrayList<>();
        for (int i = 0; i < absoluteOffsets.size() - 1; ++i) {
            Float difference = Math.abs(absoluteOffsets.get(i) - absoluteOffsets.get(i + 1));
            differences.add(difference);
        }
        return differences;
    }

    /**
     * Returns a list of Bezier control points for the list of points given. The resulting Bezier
     * spline is an approximation of the points given, i.e. it goes through the first and last
     * point. The list of distances is used to determine the parameter t of the Bezier spline.
     *
     * @param points
     * @param distances
     * @return
     */
    public static LinkedList<Vector3f> computeBezierControlpoints(
            List<Vector3f> points, List<Float> distances) {
        List<Vector3f> handles1 = new ArrayList<Vector3f>();
        List<Vector3f> handles2 = new ArrayList<Vector3f>();
        Vector3f P0 = points.get(0);
        Vector3f P3 = points.get(points.size() - 1);

        // Skip first and last point. They are fix!
        for (int i = 1; i < points.size() - 2; i += 1) {
            Vector3f k0 = points.get(i);
            Vector3f k1 = points.get(i + 1);
            float t0 = distances.get(i) / distances.get(distances.size() - 1);
            float t1 = distances.get(i + 1) / distances.get(distances.size() - 1);

            float a = 0.0f, b = 0.0f, c = 0.0f, d = 0.0f, det = 0.0f;
            Vector3f q1 = new Vector3f();
            Vector3f q2 = new Vector3f();

            a = 3 * (1 - t1) * (1 - t1) * t1;
            b = 3 * (1 - t1) * t1 * t1;
            c = 3 * (1 - t0) * (1 - t0) * t0;
            d = 3 * (1 - t0) * t0 * t0;
            det = a * d - b * c;

            q1.x = k1.x - ((1 - t1) * (1 - t1) * (1 - t1) * P0.x + t1 * t1 * t1 * P3.x);
            q1.y = k1.y - ((1 - t1) * (1 - t1) * (1 - t1) * P0.y + t1 * t1 * t1 * P3.y);
            q1.z = k1.z - ((1 - t1) * (1 - t1) * (1 - t1) * P0.z + t1 * t1 * t1 * P3.z);

            q2.x = k0.x - ((1 - t0) * (1 - t0) * (1 - t0) * P0.x + t0 * t0 * t0 * P3.x);
            q2.y = k0.y - ((1 - t0) * (1 - t0) * (1 - t0) * P0.y + t0 * t0 * t0 * P3.y);
            q2.z = k0.z - ((1 - t0) * (1 - t0) * (1 - t0) * P0.z + t0 * t0 * t0 * P3.z);

            Vector3f handle1 = new Vector3f();
            handle1.x = d * q1.x - b * q2.x;
            handle1.y = d * q1.y - b * q2.y;
            handle1.z = d * q1.z - b * q2.z;
            handle1.x /= det;
            handle1.y /= det;
            handle1.z /= det;

            Vector3f handle2 = new Vector3f();
            handle2.x = (-c) * q1.x + a * q2.x;
            handle2.y = (-c) * q1.y + a * q2.y;
            handle2.z = (-c) * q1.z + a * q2.z;
            handle2.x /= det;
            handle2.y /= det;
            handle2.z /= det;

            handles1.add(handle1);
            handles2.add(handle2);

            // Debug geometry
            //			List<Vector3f> cp = Arrays.asList( P3, handle2, handle1, P0 );
            //			Material splineMat = m_materialDefinitions.getUnshadedMaterial().clone();
            //			Spline dentalSpline = new Spline( SplineType.Bezier, cp, CURVATURE, false );
            //			Curve splineMesh = new Curve( dentalSpline, 100 );
            //			splineMesh.setLineWidth( 5.0f );
            //			final Geometry splineGeometry = new Geometry( "BezierArch", splineMesh );
            //			splineMat.setColor( "Color", ColorRGBA.Yellow );
            //			splineGeometry.setMaterial( splineMat );
            //			m_dentalArchNode.attachChild( splineGeometry );
        }

        Vector3f P1 = new Vector3f();
        for (Vector3f v : handles1) {
            P1 = P1.add(v);
        }
        P1 = P1.divide(handles1.size());

        Vector3f P2 = new Vector3f();
        for (Vector3f v : handles2) {
            P2 = P2.add(v);
        }
        P2 = P2.divide(handles2.size());

        return new LinkedList<Vector3f>(Arrays.asList(P0, P1, P2, P3));
    }

    public static Vector3f getRowAxis(int iRow, Matrix4f mat) {
        return new Vector3f(mat.get(iRow, 0), mat.get(iRow, 1), mat.get(iRow, 2));
    }

    public static Vector3f getColumnAxis(int iCol, Matrix4f mat) {
        float[] col = mat.getColumn(iCol);

        return new Vector3f(col[0], col[1], col[2]);
    }

    public static void setRowAxis(Vector3f row, int iRow, Matrix4f mat) {
        mat.set(iRow, 0, row.x);
        mat.set(iRow, 1, row.y);
        mat.set(iRow, 2, row.z);
    }

    public static void setColumnAxis(Vector3f col, int iCol, Matrix4f mat) {
        mat.set(0, iCol, col.x);
        mat.set(1, iCol, col.y);
        mat.set(2, iCol, col.z);
    }

    /**
     * Function which can be used to compare two quaternions. It returns the cosine of the angles
     * between each axes
     *
     * @param quat1
     * @param quat2
     * @return The cosines of the angles between x-,y-, and z-axes
     */
    public static Vector3f getAnglesBetweenAxes(Quaternion quat1, Quaternion quat2) {
        Vector3f x1 = new Vector3f();
        Vector3f y1 = new Vector3f();
        Vector3f z1 = new Vector3f();

        Vector3f x2 = new Vector3f();
        Vector3f y2 = new Vector3f();
        Vector3f z2 = new Vector3f();

        quat1.toAxes(new Vector3f[] {x1, y1, z1});
        quat2.toAxes(new Vector3f[] {x2, y2, z2});

        Vector3f v3AxesAngles = new Vector3f();

        v3AxesAngles.x = x1.dot(x2);
        v3AxesAngles.y = y1.dot(y2);
        v3AxesAngles.z = z1.dot(z2);

        return v3AxesAngles;
    }

    /**
     * Returns the fractional part of a float number
     *
     * @param value
     * @return
     */
    public static float fract(float value) {
        return (float) fract((double) value);
    }

    /**
     * Returns the fractional part of a double number
     *
     * @param value
     * @return
     */
    public static double fract(double value) {
        return value - Math.floor(value);
    }

    public static float radToDeg(float rad) {
        return rad * (180.0f / (float) Math.PI);
    }

    public static double radToDeg(double rad) {
        return rad * (180.0 / Math.PI);
    }

    public static float degToRad(float deg) {
        return deg * ((float) Math.PI / 180.0f);
    }

    public static double degToRad(double deg) {
        return deg * (Math.PI / 180.0);
    }

    public static boolean withinFDIInterval(int fdiNumber, int begin, int end) {
        if (begin / 10 != end / 10) {
            return (fdiNumber <= begin && fdiNumber / 10 == begin / 10)
                    || (fdiNumber <= end && fdiNumber / 10 == end / 10);
        }
        return (fdiNumber >= begin && fdiNumber <= end) || (fdiNumber <= begin && fdiNumber >= end);
    }

    /**
     * @param mesh
     * @param m_rotation
     * @param m_center
     */
    public static void rotateMesh(Mesh mesh, Quaternion m_rotation, Vector3f m_center) {
        VertexBuffer vertexBuffer = mesh.getBuffer(VertexBuffer.Type.Position).clone();
        VertexBuffer normalBuffer = mesh.getBuffer(VertexBuffer.Type.Normal).clone();
        Matrix3f matrix = Matrix3f.IDENTITY.clone();
        m_rotation.toRotationMatrix(matrix);
        float center_x = m_center.x;
        float center_y = m_center.y;
        float center_z = m_center.z;
        for (int i = 0; i < vertexBuffer.getNumElements(); ++i) {
            // rotate vertex
            float x = (float) vertexBuffer.getElementComponent(i, 0);
            float y = (float) vertexBuffer.getElementComponent(i, 1);
            float z = (float) vertexBuffer.getElementComponent(i, 2);
            Vector3f position = new Vector3f(x - center_x, y - center_y, z - center_z);

            Vector3f newPosition = matrix.mult(position);

            vertexBuffer.setElementComponent(i, 0, newPosition.x + center_x);
            vertexBuffer.setElementComponent(i, 1, newPosition.y + center_y);
            vertexBuffer.setElementComponent(i, 2, newPosition.z + center_z);

            // rotate normal
            float nx = (float) normalBuffer.getElementComponent(i, 0);
            float ny = (float) normalBuffer.getElementComponent(i, 1);
            float nz = (float) normalBuffer.getElementComponent(i, 2);
            Vector3f normal = new Vector3f(nx, ny, nz);

            Vector3f newNormal = matrix.mult(normal);

            normalBuffer.setElementComponent(i, 0, newNormal.x);
            normalBuffer.setElementComponent(i, 1, newNormal.y);
            normalBuffer.setElementComponent(i, 2, newNormal.z);
        }

        mesh.clearBuffer(VertexBuffer.Type.Position);
        mesh.setBuffer(vertexBuffer);
        mesh.clearBuffer(VertexBuffer.Type.Normal);
        mesh.setBuffer(normalBuffer);
    }

    /**
     * Convert a list of {@link Vector3f}s to a float array, i.e. sequentially lay out the
     * coordinates as they appear in the list resulting in an array of the form {x,y,z,x,y,z,...}.
     * Null objects that appear in the list are filled with zero-vectors.
     *
     * @param vectorList set of vectors to be laid out in a flat array
     * @return the float array, length 0 for entry of null-object or empty list
     */
    public static float[] convertToArray(List<Vector3f> vectorList) {
        if (vectorList == null) {
            return new float[0];
        }

        float[] array = new float[vectorList.size() * 3];

        for (int i = 0; i < vectorList.size(); ++i) {
            final Vector3f v = vectorList.get(i) != null ? vectorList.get(i) : Vector3f.ZERO;
            array[i * 3] = v.x;
            array[i * 3 + 1] = v.y;
            array[i * 3 + 2] = v.z;
        }
        return array;
    }

    /**
     * Convert a {@code float} array to a {@link List} of {@link Vector3f}s, i.e. combine every 3
     * consecutive floats to a {@link Vector3f} and add them to a {@link List} in the same order
     *
     * @param array to convert
     * @return the {@link List} containing {@code (int)(array.length / 3)} {@link Vector3f}s or that
     *     is empty (for {@code array.length == 0} or {@code null})
     */
    public static List<Vector3f> convertToVec3List(float[] array) {
        List<Vector3f> list = new ArrayList<>();
        if (array != null) {
            if (array.length == 0) {
                return list;
            }

            for (int i = 0; i < (array.length); i += 3) {
                list.add(new Vector3f(array[i], array[i + 1], array[i + 2]));
            }
        }
        return list;
    }

    /**
     * Convert a {@link Matrix4f} to a column-major float array
     *
     * @param matrix to be laid out in a flat array, in column-major order
     * @return the float array, length 0 for entry of <code>null</code>-object
     */
    public static float[] convertToColumnMajorArray(Matrix4f matrix) {
        if (matrix == null) {
            return new float[0];
        }

        float[] arr = new float[16];
        matrix.get(arr, false);
        return arr;
    }

    /**
     * Convert a {@link Matrix3f} to a column-major float array
     *
     * @param matrix to be laid out in a flat array, in column-major order
     * @return the float array, length 0 for entry of <code>null</code>-object
     */
    public static float[] convertToColumnMajorArray(Matrix3f matrix) {
        if (matrix == null) {
            return new float[0];
        }

        float[] arr = new float[9];
        matrix.get(arr, false);
        return arr;
    }

    public static final double HALF_PI = Math.PI / 2.0;
}
