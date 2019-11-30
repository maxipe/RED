package UserType

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.StringType
import org.hibernate.usertype.UserType
import org.joda.money.Money

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

class MoneyUserType implements UserType {
    /**
     * Return the SQL type codes for the columns mapped by this type. The
     * codes are defined on <tt>java.sql.Types</tt>.
     * @see java.sql.Types* @return int[] the typecodes
     */
    @Override
    int[] sqlTypes() {
        [Types.NVARCHAR] as int[]
    }

    /**
     * The class returned by <tt>nullSafeGet()</tt>.
     *
     * @return Class
     */
    @Override
    Class returnedClass() {
        Money
    }

    /**
     * Compare two instances of the class mapped by this type for persistence "equality".
     * Equality of the persistent state.
     *
     * @param x
     * @param y
     * @return boolean
     */
    @Override
    boolean equals(Object x, Object y) throws HibernateException {
        x == y
    }

    /**
     * Get a hashcode for the instance, consistent with persistence "equality"
     */
    @Override
    int hashCode(Object x) throws HibernateException {
        x?.hashCode() ?: 0
    }

    /**
     * Retrieve an instance of the mapped class from a JDBC resultset. Implementors
     * should handle possibility of null values.
     *
     *
     * @param rs a JDBC result set
     * @param names the column names
     * @param session
     * @param owner the containing entity  @return Object
     * @throws HibernateException* @throws SQLException
     */
    @Override
    Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String moneyStr = StringType.INSTANCE.get(rs, names[0], session)

        moneyStr ? Money.parse(moneyStr) : null
    }

    /**
     * Write an instance of the mapped class to a prepared statement. Implementors
     * should handle possibility of null values. A multi-column type should be written
     * to parameters starting from <tt>index</tt>.
     *
     *
     * @param st a JDBC prepared statement
     * @param value the object to write
     * @param index statement parameter index
     * @param session
     * @throws HibernateException* @throws SQLException
     */
    @Override
    void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {

        StringType.INSTANCE.set(st, value ? value.toString() : null, index, session)
    }

    /**
     * Return a deep copy of the persistent state, stopping at entities and at
     * collections. It is not necessary to copy immutable objects, or null
     * values, in which case it is safe to simply return the argument.
     *
     * @param value the object to be cloned, which may be null
     * @return Object a copy
     */
    @Override
    Object deepCopy(Object value) throws HibernateException {
        value
    }

    /**
     * Are objects of this type mutable?
     *
     * @return boolean
     */
    @Override
    boolean isMutable() {
        return false
    }

    /**
     * Transform the object into its cacheable representation. At the very least this
     * method should perform a deep copy if the type is mutable. That may not be enough
     * for some implementations, however; for example, associations must be cached as
     * identifier values. (optional operation)
     *
     * @param value the object to be cached
     * @return a cachable representation of the object
     * @throws HibernateException
     */
    @Override
    Serializable disassemble(Object value) throws HibernateException {
        throw new UnsupportedOperationException("No soportado")
    }

    /**
     * Reconstruct an object from the cacheable representation. At the very least this
     * method should perform a deep copy if the type is mutable. (optional operation)
     *
     * @param cached the object to be cached
     * @param owner the owner of the cached object
     * @return a reconstructed object from the cachable representation
     * @throws HibernateException
     */
    @Override
    Object assemble(Serializable cached, Object owner) throws HibernateException {
        throw new UnsupportedOperationException("No soportado")
    }

    /**
     * During merge, replace the existing (target) value in the entity we are merging to
     * with a new (original) value from the detached entity we are merging. For immutable
     * objects, or null values, it is safe to simply return the first parameter. For
     * mutable objects, it is safe to return a copy of the first parameter. For objects
     * with component values, it might make sense to recursively replace component values.
     *
     * @param original the value from the detached entity being merged
     * @param target the value in the managed entity
     * @param owner @return the value to be merged
     */
    @Override
    Object replace(Object original, Object target, Object owner) throws HibernateException {
        throw new UnsupportedOperationException("No soportado")
    }
}
