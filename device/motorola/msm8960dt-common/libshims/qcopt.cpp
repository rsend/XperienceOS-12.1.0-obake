/*
 * Compatibility for the pre-Oreo libqc-opt Skia ABI.
 *
 * SkCanvas::drawRectCoords() was replaced by drawRect(SkRect, SkPaint).
 * Keep the old exported symbol used by the proprietary library and forward
 * it to the supported Oreo implementation.
 */

#include <SkCanvas.h>
#include <SkPaint.h>
#include <SkRect.h>

extern "C" void _ZN8SkCanvas14drawRectCoordsEffffRK7SkPaint(
        SkCanvas* canvas, float left, float top, float right, float bottom,
        const SkPaint& paint) {
    canvas->drawRect(SkRect::MakeLTRB(left, top, right, bottom), paint);
}
