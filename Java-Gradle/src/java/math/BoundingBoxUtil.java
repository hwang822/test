package math;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitorAdapter;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.VertexBuffer.Type;
import data.Denture;
import data.Jaw;
import data.KfoData;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Creation of axis aligned bounding boxes from given mesh or FloatBuffer of vertices. The box may
 * be centered around a given Vector3f point.
 *
 * @ingroup ViewData.math
 * @author Jonas Mostert, Martin Schumann
 * @date 02.07.2014
 * @version $Id$
 * @copyright 2014 CA Digital GmbH. All rights reserved.
 */
public final class BoundingBoxUtil {
    public static BoundingBox computeAABBfromVertices(FloatBuffer vertices) {
        Vector3f min =
                new Vector3f(
                        Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        Vector3f max =
                new Vector3f(
                        Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

        vertices.rewind();
        while (vertices.hasRemaining()) {
            float x = vertices.get();
            float y = vertices.get();
            float z = vertices.get();

            if (x < min.x) min.x = x;
            if (y < min.y) min.y = y;
            if (z < min.z) min.z = z;

            if (x > max.x) max.x = x;
            if (y > max.y) max.y = y;
            if (z > max.z) max.z = z;
        }

        vertices.rewind();

        BoundingBox bboxCalculated = new BoundingBox(min, max);
        return bboxCalculated;
    }

    public static BoundingBox computeAABBfromSpatialList(
            List<Spatial> list, boolean showOnlyKfoDatas) {
        final List<Geometry> geometries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i).getName().contains("Root")
                            && !list.get(i).getName().contains("SceneRoot"))
                    && !list.get(i).getName().contains("Sky")) {
                list.get(i)
                        .breadthFirstTraversal(
                                new SceneGraphVisitorAdapter() {
                                    @Override
                                    public void visit(Geometry geom) {
                                        KfoData<?> data = geom.getUserData(KfoData.USER_DATA_KEY);

                                        if (!geom.getCullHint().equals(CullHint.Always)
                                                && (data != null || !showOnlyKfoDatas))
                                            geometries.add(geom);
                                    }
                                });
            }
        }

        Vector3f min =
                new Vector3f(
                        Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        Vector3f max =
                new Vector3f(
                        Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        for (Geometry child : geometries) {
            BoundingBox box = (BoundingBox) child.getMesh().getBound();

            Vector3f min_child = box.getMin(null);
            Vector3f max_child = box.getMax(null);

            min.x = Math.min(min.x, min_child.x);
            min.y = Math.min(min.y, min_child.y);
            min.z = Math.min(min.z, min_child.z);

            max.x = Math.max(max.x, max_child.x);
            max.y = Math.max(max.y, max_child.y);
            max.z = Math.max(max.z, max_child.z);
        }

        BoundingBox bboxCalculated = new BoundingBox(min, max);
        return bboxCalculated;
    }

    public static Vector3f computeCenterAlongAxis(Node m_panoramaScene, Vector3f m_upVectorBukkal) {
        List<Spatial> spatialList = m_panoramaScene.getChildren();

        return computeCenterAlongAxisForSpatialList(m_upVectorBukkal, spatialList);
    }

    public static Vector3f computeCenterAlongAxisForSpatialList(
            Vector3f m_upVectorBukkal, List<Spatial> spatialList) {
        float min = Float.POSITIVE_INFINITY;
        float max = Float.NEGATIVE_INFINITY;
        for (Spatial spatial : spatialList) {
            if (spatial instanceof Geometry) {
                Geometry geo = (Geometry) spatial;
                FloatBuffer vertices = geo.getMesh().getFloatBuffer(Type.Position);
                vertices.rewind();

                while (vertices.hasRemaining()) {
                    float x = vertices.get();
                    float y = vertices.get();
                    float z = vertices.get();

                    Vector3f pos = new Vector3f(x, y, z);
                    float dot = pos.dot(m_upVectorBukkal);

                    if (dot < min) min = dot;
                    if (dot > max) max = dot;
                }
                vertices.rewind();
            }
        }
        return m_upVectorBukkal.mult((max - min) / 2.f + min);
    }

    /**
     * @param list
     * @param b
     * @return
     */
    public static BoundingBox computeExtentsOfDenture(Denture denture) {
        Vector3f min = new Vector3f(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
        Vector3f max = new Vector3f(-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE);

        for (Jaw jaw : denture.getJaws()) {
            FloatBuffer vertices = jaw.getMesh().getFloatBuffer(Type.Position);
            vertices.rewind();

            int i = 0;
            while (vertices.hasRemaining()) {
                float p = vertices.get();

                if (p < min.get(i)) min.set(i, p);
                if (p > max.get(i)) max.set(i, p);

                i = (i + 1) % 3;
            }
            vertices.rewind();
        }
        return new BoundingBox(min, max);
    }
}
